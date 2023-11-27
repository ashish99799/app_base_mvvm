package com.base.app.testing.widgets.refresh

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

object DensityUtils {
    fun dip2px(context: Context, var1: Float): Int {
        return density2px(context, var1, false)
    }

    fun px2dip(context: Context, var1: Float): Int {
        return px2density(context, var1, false)
    }

    fun sp2px(context: Context, var1: Float): Int {
        return density2px(context, var1, true)
    }

    fun px2sp(context: Context, var1: Float): Int {
        return px2density(context, var1, true)
    }

    private fun density2px(context: Context, var1: Float, isScaled: Boolean): Int {
        return XXtoXX(var1, context, isScaled, true)
    }

    private fun px2density(context: Context, var1: Float, isScaled: Boolean): Int {
        return XXtoXX(var1, context, isScaled, false)
    }

    private fun XXtoXX(var1: Float, context: Context, isScaled: Boolean, toPx: Boolean): Int {
        val metrics = context.resources.displayMetrics
        val var2 = if (isScaled) metrics.scaledDensity else metrics.density
        return if (toPx) (var1 * var2 + 0.5f).toInt() else (var1 / var2 + 0.5f).toInt()
    }

    fun getScreenHeight(context: Context): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)
        return outMetrics.heightPixels
    }

    fun getScreenWidth(context: Context): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)
        return outMetrics.widthPixels
    }
}