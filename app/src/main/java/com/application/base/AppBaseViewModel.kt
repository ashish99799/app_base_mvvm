package com.application.base

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.*
import org.json.JSONObject
import retrofit2.HttpException

abstract class AppBaseViewModel : ViewModel() {

    private var context: Context? = null

    // T => Template type
    fun <T> callApi(
        mContext: Context,
        observable: Observable<T>,
        onResponse: (T) -> Unit,
        noInternet: () -> Unit,
        onStart: () -> Unit,
        onFinish: () -> Unit,
        onAuthFail: () -> Unit
    ) {
        context = mContext
        // Check Internet connectivity
        if (!mContext.isNetworkConnectionAvailable()) {
            noInternet.invoke()
            return
        }

        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<T> {
                override fun onSubscribe(d: Disposable) {
                    onStart.invoke()
                }

                override fun onNext(response: T) {
                    Log.e("API", "Success : ${Gson().toJson(response)}")
                    onResponse(response)
                }

                override fun onError(e: Throwable) {
                    onFinish.invoke()
                    onResponseFailure(context!!, e) {
                        onAuthFail.invoke()
                    }
                }

                override fun onComplete() {
                    onFinish.invoke()
                }
            })
    }

    fun onResponseFailure(mContext: Context, throwable: Throwable, onAuthFail: () -> Unit) {
        context = mContext
        val error: HttpException = throwable as HttpException
        Log.e("API", "Error : ${error.message ?: ""}")
        Log.e("API", "Error : ${error.code()}")
        when (error.code()) {
            // InValidateData
            422 -> {
                val errorRawData = throwable.response()!!.errorBody().toString()
                if (errorRawData.isNotEmpty()) {
                    if (errorRawData.contains("{") && errorRawData.contains(mContext.getErrorMessage())) {
                        errorDialog(
                            JSONObject(errorRawData).getString(mContext.getErrorMessage()),
                            "Alert"
                        )
                    } else {
                        errorDialog(errorRawData)
                    }
                }
            }
            // Unauthenticated
            401 -> {
                val errorRawData = throwable.message
                if (!errorRawData.isNullOrEmpty()) {
                    if (errorRawData.contains("{") && errorRawData.contains(mContext.getErrorMessage())) {
                        context!!.alert(JSONObject(errorRawData).getString(mContext.getErrorMessage()), "Alert") {
                            okButton { onAuthFail.invoke() }
                        }.show()
                    } else {
                        errorDialog(errorRawData.toString())
                    }
                } else {
                    onAuthFail.invoke()
                }
            }
            // ForceUpdate
            426 -> {

            }
            // ServerError
            500 -> errorDialog("Internal server error")
            // PageNotFound
            404 -> errorDialog("Page not found")
            // BadRequest, Unauthorized, RequestTimeOut, Conflict, Blocked
            400, 403, 408, 409, 423 -> {
                val errorRawData = throwable.message
                if (!errorRawData.isNullOrEmpty()) {
                    if (errorRawData.contains("{") && errorRawData.contains(mContext.getErrorMessage())) {
                        errorDialog(JSONObject(errorRawData).getString(mContext.getErrorMessage()))
                    } else {
                        errorDialog(errorRawData)
                    }
                }
            }
        }
    }

    private fun errorDialog(optString: String, title: String = context!!.getString(R.string.app_name)) {
//        toastError(optString)
        context!!.alert(optString, title) { okButton { } }.build().show()
    }

    private fun toastError(message: String) {
        context!!.toast(message)
    }

}