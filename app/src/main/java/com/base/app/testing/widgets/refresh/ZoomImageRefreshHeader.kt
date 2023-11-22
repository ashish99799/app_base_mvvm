package com.base.app.testing.widgets.refresh

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.ColorInt
import com.scwang.smartrefresh.layout.api.RefreshHeader
import com.scwang.smartrefresh.layout.api.RefreshKernel
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.RefreshState
import com.scwang.smartrefresh.layout.constant.SpinnerStyle

class ZoomImageRefreshHeader @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ImageView(context, attrs, defStyleAttr), RefreshHeader {

    private var isSupportHorizontalDrag: Boolean = false

    init {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        scaleType = ScaleType.CENTER_CROP
        if (attrs == null) {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }

    override fun onPullingDown(percent: Float, offset: Int, headerHeight: Int, extendHeight: Int) {}
    override fun onReleasing(percent: Float, offset: Int, headerHeight: Int, extendHeight: Int) {}
    override fun getView(): View {
        return this
    }

    override fun getSpinnerStyle(): SpinnerStyle {
        return SpinnerStyle.Scale
    }

    override fun setPrimaryColors(@ColorInt vararg colors: Int) {}
    override fun onInitialized(kernel: RefreshKernel, height: Int, extendHeight: Int) {
        kernel.setStateRefresing()
    }

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