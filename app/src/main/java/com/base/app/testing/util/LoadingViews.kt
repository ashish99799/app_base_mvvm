package com.base.app.testing.util

import android.content.Context
import android.view.View
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.scwang.smartrefresh.layout.SmartRefreshLayout

interface LoadingViews {

    fun showLoadingView(view: View, context: Context? = null) {
        when (view) {
            is SwipeRefreshLayout -> view.isRefreshing = true
            is SmartRefreshLayout -> {}
            else -> context?.showDialog()
        }
    }

    fun hideLoadingView(view: View) {
        when (view) {
            is SwipeRefreshLayout -> view.isRefreshing = false
            is SmartRefreshLayout -> {
                if (view.isRefreshing) view.finishRefresh()
                if (view.isLoading) view.finishLoadmore()
            }
            else -> hideDialog()
        }
    }
}