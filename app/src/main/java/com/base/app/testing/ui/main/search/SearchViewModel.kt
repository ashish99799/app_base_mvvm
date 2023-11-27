package com.base.app.testing.ui.main.search

import android.content.Context
import com.application.base.AppBaseViewModel
import com.base.app.testing.MyApplication.Companion.appRepository
import com.base.app.testing.model.responses.RowData
import com.base.app.testing.util.LoadingViews
import com.base.app.testing.util.checkNetworkConnectionDialog
import com.scwang.smartrefresh.layout.SmartRefreshLayout

class SearchViewModel : AppBaseViewModel(), LoadingViews {

    companion object {
        lateinit var mContext: Context

        @JvmStatic
        fun newInstance(context: Context) = SearchViewModel().apply {
            mContext = context
        }
    }

    fun getSearchUser(
        query: String, page: Int,
        smartRefreshLayout: SmartRefreshLayout,
        responseBlock: (ArrayList<RowData>) -> Unit
    ) {
        // API Calling
        callApi(mContext, appRepository.getSearchUser(query, page),
            onResponse = {
                responseBlock((it.items ?: arrayListOf()))
            }, noInternet = {
                hideLoadingView(smartRefreshLayout)
                mContext.checkNetworkConnectionDialog()
            }, onStart = {
                showLoadingView(smartRefreshLayout)
            }, onAuthFail = {
                // Clear AppPref and going to Login Page
            }, onFinish = {
                hideLoadingView(smartRefreshLayout)
            }, onError = {

            }
        )
    }

}