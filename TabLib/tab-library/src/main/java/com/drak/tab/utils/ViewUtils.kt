package com.drak.tab.utils

import android.graphics.Bitmap
import android.view.View
import androidx.annotation.IdRes

/**
 * @author dark
 */
object ViewUtils {
    @JvmStatic
    fun generateViewCacheBitmap(view: View): Bitmap {
        view.destroyDrawingCache()
        val widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(
            0,
            View.MeasureSpec.UNSPECIFIED
        )
        val heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(
            0,
            View.MeasureSpec.UNSPECIFIED
        )
        view.measure(widthMeasureSpec, heightMeasureSpec)
        val width = view.measuredWidth
        val height = view.measuredHeight
        view.layout(0, 0, width, height)
        view.isDrawingCacheEnabled = true
        view.buildDrawingCache()
        return Bitmap.createBitmap(view.drawingCache)
    }

    @JvmStatic
    fun findBrotherView(
        view: View,
        @IdRes id: Int,
        level: Int
    ): View? {
        var count = 0
        var temp = view
        while (count < level) {
            val target = temp.findViewById<View>(id)
            if (target != null) {
                return target
            }
            count += 1
            temp = if (temp.parent is View) {
                temp.parent as View
            } else {
                break
            }
        }
        return null
    }
}