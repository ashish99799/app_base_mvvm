package com.base.app.testing.ui.main.github_user

import android.content.Context
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.application.base.AppBaseViewModel
import com.base.app.testing.MyApplication.Companion.appRepository
import com.base.app.testing.model.responses.UserData
import com.base.app.testing.model.responses.UserRepoData
import com.base.app.testing.ui.main.search.SearchViewModel
import com.base.app.testing.util.LoadingViews
import com.base.app.testing.util.checkNetworkConnectionDialog

class GithubUserViewModel : AppBaseViewModel(), LoadingViews {

    companion object {
        lateinit var mContext: Context

        @JvmStatic
        fun newInstance(context: Context) = GithubUserViewModel().apply {
            mContext = context
        }
    }

    fun onUserInfo(query: String, responseBack: (UserData, String) -> Unit) {
        // API Calling
        callApi(mContext, appRepository.onUserInfo(query),
            onResponse = {
                responseBack(it, "")
            }, noInternet = {
                mContext.checkNetworkConnectionDialog()
            }, onStart = {

            }, onAuthFail = {
                // Clear AppPref and going to Login Page
            }, onFinish = {

            }, onError = {

            }
        )
    }

    fun onUserRepo(query: String, swipeRefreshLayout: SwipeRefreshLayout, responseBack: (List<UserRepoData>) -> Unit) {
        // API Calling
        callApi(mContext, appRepository.onUserRepo(query),
            onResponse = {
                responseBack(it)
            }, noInternet = {
                hideLoadingView(swipeRefreshLayout)
                SearchViewModel.mContext.checkNetworkConnectionDialog()
            }, onStart = {
                showLoadingView(swipeRefreshLayout)
            }, onAuthFail = {
                // Clear AppPref and going to Login Page
            }, onFinish = {
                hideLoadingView(swipeRefreshLayout)
            }, onError = {

            }
        )
    }

}