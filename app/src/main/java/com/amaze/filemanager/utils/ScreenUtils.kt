package com.amaze.filemanager.utils

import android.app.Activity
import android.util.DisplayMetrics
import java.lang.ref.WeakReference
import kotlin.math.roundToInt

class ScreenUtils(act: Activity) {
    private val _activity: WeakReference<Activity> = WeakReference(act)
    private val activity: Activity?
        get() = _activity.get()

    /**
     * Converts Density Pixels to real Pixels in screen
     * It uses context to retrieve the density.
     */
    fun convertDbToPx(dp: Float): Int =
        activity?.let {
            (it.resources.displayMetrics.density * dp).roundToInt()
        } ?: 0

    /**
     * Converts real Pixels in screen to Density Pixels
     * It uses context to retrieve the density.
     */
    fun convertPxToDb(px: Float): Int =
        activity?.let {
            (px / it.resources.displayMetrics.density).roundToInt()
        } ?: 0

    private val screenWidthInPx: Int
        get() {
            val displayMetrics = DisplayMetrics()
            activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
            return displayMetrics.widthPixels
        }

    private val screenHeightInPx: Int
        get() {
            val displayMetrics = DisplayMetrics()
            activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
            return displayMetrics.heightPixels
        }

    val screenWidthInDp: Int
        get() = convertPxToDb(screenWidthInPx.toFloat())
    val screeHeightInDb: Int
        get() = convertPxToDb(screenHeightInPx.toFloat())

    companion object {
        const val TOOLBAR_HEIGHT_IN_DP = 128 // 160 dpi
    }
}
