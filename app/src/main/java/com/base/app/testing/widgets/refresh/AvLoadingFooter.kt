package com.base.app.testing.widgets.refresh

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.annotation.ColorInt
import com.application.base.R
import com.scwang.smartrefresh.layout.api.RefreshFooter
import com.scwang.smartrefresh.layout.api.RefreshKernel
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.RefreshState
import com.scwang.smartrefresh.layout.constant.SpinnerStyle

class AvLoadingFooter(context: Context?) : RelativeLayout(context), RefreshFooter {

    val isSupportHorizontalDrag: Boolean
        get() = false

    init {
        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        LayoutInflater.from(context).inflate(R.layout.loading_footer, this)
    }

    override fun onPullingUp(percent: Float, offset: Int, footerHeight: Int, extendHeight: Int) {}
    override fun onPullReleasing(percent: Float, offset: Int, footerHeight: Int, extendHeight: Int) {}
    override fun setLoadmoreFinished(finished: Boolean): Boolean {
        return false
    }

    override fun getView(): View {
        return this
    }

    override fun getSpinnerStyle(): SpinnerStyle {
        return SpinnerStyle.Translate
    }

    override fun setPrimaryColors(@ColorInt vararg colors: Int) {}
    override fun onInitialized(kernel: RefreshKernel, height: Int, extendHeight: Int) {}
    override fun onHorizontalDrag(percentX: Float, offsetX: Int, offsetMax: Int) {}
    override fun onStartAnimator(layout: RefreshLayout, height: Int, extendHeight: Int) {}
    override fun onFinish(layout: RefreshLayout, success: Boolean): Int {
        return 0
    }

    override fun isSupportHorizontalDrag(): Boolean {
        return isSupportHorizontalDrag
    }

    override fun onStateChanged(refreshLayout: RefreshLayout, oldState: RefreshState, newState: RefreshState) {}
}