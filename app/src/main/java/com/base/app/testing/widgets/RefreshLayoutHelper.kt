package com.base.app.testing.widgets

import com.base.app.testing.widgets.refresh.AvLoadingFooter
import com.base.app.testing.widgets.refresh.AvLoadingHeader
import com.base.app.testing.widgets.refresh.DensityUtils
import com.base.app.testing.widgets.refresh.ZoomImageRefreshHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshFooter
import com.scwang.smartrefresh.layout.api.RefreshHeader
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener


object RefreshLayoutHelper {
    fun init(mSmartRefresh: SmartRefreshLayout?) {
        requireNotNull(mSmartRefresh)
        mSmartRefresh.setOnRefreshLoadmoreListener(object : OnRefreshLoadmoreListener {
            override fun onLoadmore(refreshlayout: RefreshLayout) {
                refreshlayout.finishLoadmore()
            }

            override fun onRefresh(refreshlayout: RefreshLayout) {
                refreshlayout.finishRefresh()
            }
        })
    }

    private fun init(mSmartRefresh: SmartRefreshLayout?, headerView: RefreshHeader?, listener: OnRefreshListener?) {
        requireNotNull(mSmartRefresh)
        init(mSmartRefresh, headerView, null, listener, null)
    }

    private fun init(mSmartRefresh: SmartRefreshLayout?, footerView: RefreshFooter?, listener: OnLoadmoreListener?) {
        requireNotNull(mSmartRefresh)
        init(mSmartRefresh, null, footerView, null, listener)
    }

    private fun init(
        mSmartRefresh: SmartRefreshLayout?,
        headerView: RefreshHeader?, footerView: RefreshFooter?,
        onRefreshListener: OnRefreshListener?, onLoadmoreListener: OnLoadmoreListener?
    ) {
        requireNotNull(mSmartRefresh)
        if (headerView != null) {
            mSmartRefresh.refreshHeader = headerView
        }
        if (footerView != null) {
            mSmartRefresh.refreshFooter = footerView
        }
        if (onRefreshListener != null) {
            mSmartRefresh.setOnRefreshListener(onRefreshListener)
        }
        if (onLoadmoreListener != null) {
            mSmartRefresh.setOnLoadmoreListener(onLoadmoreListener)
        }
        initRefreshLayout(mSmartRefresh)
    }

    private fun initRefreshLayout(layout: RefreshLayout) {
        layout.isEnableScrollContentWhenLoaded = false
        layout.setDragRate((2.0).toFloat())
        layout.setReboundDuration(500)
    }

    fun initToRefreshStyle(mSmartRefresh: SmartRefreshLayout?, listener: OnRefreshListener?) {
        requireNotNull(mSmartRefresh)
        init(mSmartRefresh, AvLoadingHeader(mSmartRefresh.context), listener)
    }

    fun initToLoadMoreStyle(mSmartRefresh: SmartRefreshLayout?, listener: OnLoadmoreListener?) {
        requireNotNull(mSmartRefresh)
        val context = mSmartRefresh.context
        init(mSmartRefresh, AvLoadingFooter(context), listener)
    }

    @JvmOverloads
    fun initToZoomImageStyle(mSmartRefresh: SmartRefreshLayout?, dip: Int = 0) {
        requireNotNull(mSmartRefresh)
        val header = ZoomImageRefreshHeader(mSmartRefresh.context)
        if (dip != 0) {
            val params = header.layoutParams
            params.height = DensityUtils.dip2px(header.context, dip.toFloat())
        }
        mSmartRefresh.setHeaderMaxDragRate(1.7f)
        init(mSmartRefresh, header) { refreshlayout: RefreshLayout? -> }
    }
}