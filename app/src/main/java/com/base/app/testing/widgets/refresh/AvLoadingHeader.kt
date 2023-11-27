package com.base.app.testing.widgets.refresh

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.annotation.ColorInt
import com.airbnb.lottie.LottieAnimationView
import com.base.app.testing.R
import com.scwang.smartrefresh.layout.api.RefreshHeader
import com.scwang.smartrefresh.layout.api.RefreshKernel
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.RefreshState
import com.scwang.smartrefresh.layout.constant.SpinnerStyle

class AvLoadingHeader(context: Context) : RelativeLayout(context), RefreshHeader {

    private var isSupportHorizontalDrag: Boolean = false

    private val lottieAnimation: LottieAnimationView?

    init {
        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val mInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val inflate = mInflater.inflate(R.layout.loading_header, this)
        lottieAnimation = inflate.findViewById(R.id.my_loading_view)
    }

    fun playAnimation() {
        if (lottieAnimation == null) {
            return
        }
        if (lottieAnimation.isAnimating) {
            return
        }
        lottieAnimation.setAnimation("loading.json")
        lottieAnimation.playAnimation()
    }

    fun cancelAnimation() {
        if (lottieAnimation == null) {
            return
        }
        if (lottieAnimation.isAnimating) {
            lottieAnimation.cancelAnimation()
        }
    }

    override fun onPullingDown(percent: Float, offset: Int, headerHeight: Int, extendHeight: Int) {}

    override fun onReleasing(percent: Float, offset: Int, headerHeight: Int, extendHeight: Int) {}

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

    override fun onStateChanged(refreshLayout: RefreshLayout, oldState: RefreshState, newState: RefreshState) {
//        LogUtils.i("xiaowu==" + oldState.name() + "       " + newState.name());
        if (oldState == RefreshState.None && newState == RefreshState.PullDownToRefresh) {
            playAnimation()
        } else if (oldState == RefreshState.RefreshFinish && newState == RefreshState.None) {
            cancelAnimation()
        }
    }
}