package com.base.app.testing.util

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import com.application.base.R
import com.application.base.databinding.ProgressBinding
import com.base.app.testing.model.responses.GymList
import com.base.app.testing.model.responses.JsonData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.anko.toast
import java.io.IOException

const val INTENT_DATA = "INTENT_DATA"
const val BASE_URL = "https://api.github.com/"

internal var alertDialog: AlertDialog? = null
internal var SpinKitProgressDialog: Dialog? = null

var doubleBackToExitPressedOnce = false

var mSports = arrayListOf("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "")

enum class Status {
    PENDING,
    RUNNING,
    FINISHED
}

fun Context.checkBackPress() {
    doubleBackToExitPressedOnce = true
    toast(getString(R.string.please_click_back_again_to_exit))
    Handler(Looper.getMainLooper()).postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
}

fun Context.isNetworkConnectionAvailable(): Boolean {
    val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    return (activeNetwork != null && activeNetwork.isConnected)
}

fun Context.checkInternetConnection(): Boolean {
    return if (isNetworkConnectionAvailable()) {
        Log.d("Network", "Connected")
        true
    } else {
        Log.d("Network", "Not Connected")
        checkNetworkConnectionDialog()
        false
    }
}

fun Context.checkNetworkConnectionDialog() {
    if (alertDialog != null) {
        if (alertDialog!!.isShowing) {
            return
        }
    }

    alertDialog = AlertDialog.Builder(this, R.style.DialogTheme)
        .setTitle(resources.getString(R.string.no_connection))
        .setMessage(resources.getString(R.string.turn_on_connection))
        .setNegativeButton(getString(R.string.dialog_ok)) { dialog, which -> dialog.dismiss() }.create()
    alertDialog!!.show()
}

fun Context.showDialog() {
    // implementation 'com.github.ybq:Android-SpinKit:1.2.0'
    if (SpinKitProgressDialog != null) {
        if (SpinKitProgressDialog!!.isShowing) {
            return
        }
    }

    SpinKitProgressDialog = Dialog(this).also {
        it.requestWindowFeature(Window.FEATURE_NO_TITLE)
        it.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        it.setContentView(ProgressBinding.inflate(LayoutInflater.from(this)).root)
        it.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        it.window!!.setDimAmount(0.3f)
        it.setCancelable(false)
        it.window!!.attributes = WindowManager.LayoutParams().also { wm ->
            wm.copyFrom(it.window!!.attributes)
            wm.width = WindowManager.LayoutParams.WRAP_CONTENT
            wm.height = WindowManager.LayoutParams.WRAP_CONTENT
        }
    }
    SpinKitProgressDialog!!.show()
}

fun hideDialog() {
    if (SpinKitProgressDialog != null && SpinKitProgressDialog!!.isShowing) {
        SpinKitProgressDialog!!.dismiss()
        SpinKitProgressDialog = null
    }
}

fun ImageView.loadImage(imgUrl: Any) {
    Glide.with(this)
        .load(imgUrl)
        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
        .into(this)
}

fun Context.getJsonDataFromAsset(fileName: String): ArrayList<GymList> {
    val listData = ArrayList<GymList>()
    try {
        val jsonString = assets.open(fileName).bufferedReader().use { it.readText() }
        listData.addAll((Gson().fromJson(jsonString, JsonData::class.java)).gym_list ?: arrayListOf())
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        listData.clear()
    }
    return listData
}

// T -> Template Types
fun <T> CoroutineScope.executeAsyncTask(
    onPreExecute: () -> Unit,
    doInBackground: () -> T,
    onPostExecute: (T) -> Unit
) = launch {
    // runs in Main Thread
    onPreExecute()
    val result = withContext(Dispatchers.IO) {
        // runs in background thread without blocking the Main Thread
        doInBackground()
    }
    // runs in Main Thread
    onPostExecute(result)

    /**
     * ---- How to use with (Activity & Fragment) ----
     * lifecycleScope.executeAsyncTask()
     *
     * ---- How to use with (ViewModel) ----
     * viewModelScope.executeAsyncTask()
     *
     * ---- Example ----
     * (lifecycleScope | viewModelScope).executeAsyncTask(
     *      onPreExecute = {},
     *      doInBackground = {},
     *      onPostExecute = {}
     * )
     *
     **/
}

// P | R -> Template Types
fun <P, R> CoroutineScope.executeAsyncTask(
    onPreExecute: () -> Unit,
    doInBackground: suspend (suspend (P) -> Unit) -> R,
    onPostExecute: (R) -> Unit,
    onProgressUpdate: (P) -> Unit
) = launch {
    // runs in Main Thread
    onPreExecute()
    val result = withContext(Dispatchers.IO) {
        // runs in background thread without blocking the Main Thread
        doInBackground {
            withContext(Dispatchers.Main) {
                // runs in Main Thread
                onProgressUpdate(it)
            }
        }
    }
    // runs in Main Thread
    onPostExecute(result)

    /**
     * ---- Use with (Activity & Fragment) ----
     * lifecycleScope.executeAsyncTask()
     *
     * ---- Use with (ViewModel) ----
     * viewModelScope.executeAsyncTask()
     *
     * ---- Example ----
     * (lifecycleScope | viewModelScope).executeAsyncTask(
     *     onPreExecute = {
     *          // ... runs in Main Thread
     *     },
     *     doInBackground = { publishProgress: suspend (progress: Int) -> Unit ->
     *          // ... runs in Background Thread
     *          // simulate progress update
     *          publishProgress(50) // call `publishProgress` to update progress, `onProgressUpdate` will be called
     *          delay(1000)
     *          publishProgress(100)
     *          "Result" // send data to "onPostExecute"
     *     },
     *     onProgressUpdate = {
     *          // runs in Main Thread
     *          // ... here "it" contains progress
     *     },
     *     onPostExecute = {
     *          // runs in Main Thread
     *          // ... here "it" is a data returned from "doInBackground"
     *     }
     * )
     *
     * **/
}