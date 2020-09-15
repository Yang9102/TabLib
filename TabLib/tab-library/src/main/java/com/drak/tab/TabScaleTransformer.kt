package com.drak.tab

import android.util.Log
import android.util.TypedValue

/**
 * @author drak
 */
class TabScaleTransformer(
    private val slidingScaleTabLayout: SlidingScaleTabLayout,
    private val textSelectSize: Float, private val textUnSelectSize: Float, openDmg: Boolean
) : ITabScaleTransformer {
    private val maxScale: Float
    private val openDmg: Boolean
    override fun setNormalWidth(
        position: Int,
        width: Int,
        isSelect: Boolean
    ) {
    }

    init {
        maxScale = textSelectSize / textUnSelectSize - 1
        this.openDmg = openDmg
    }

    override fun onPageScrolled(
        position: Int,
        positionOffset: Float,
        positionOffsetPixels: Int
    ) {
        Log.i("TabScaleTransformer", "position:$position")
        // 字体大小相同，不需要切换
        if (textSelectSize == textUnSelectSize) {
            return
        }
        if (openDmg) {
            for (i in 0 until slidingScaleTabLayout.getTabCount()) {
                if (i != position && i != position + 1) {
                    changTabDmgWidth(i, 1f)
                }
            }
            changeDmgSize(position, positionOffset)
        } else {
            for (i in 0 until slidingScaleTabLayout.getTabCount()) {
                if (i != position && i != position + 1) {
                    updateTextSize(i, 1f)
                }
            }
            changeTextSize(position, positionOffset)
        }
    }

    private fun changeTextSize(position: Int, positionOffset: Float) {
        updateTextSize(position, positionOffset)
        if (position + 1 < slidingScaleTabLayout.getTabCount()) {
            updateTextSize(position + 1, 1 - positionOffset)
        }
    }

    private fun updateTextSize(position: Int, positionOffset: Float) {
        val currentTab = slidingScaleTabLayout.getTitle(position)
        // 必须要在View调用post更新样式，否则可能无效
        currentTab!!.post {
            val textSize =
                (textSelectSize - Math.abs((textSelectSize - textUnSelectSize) * positionOffset)).toInt()
            if (currentTab.textSize != textSize.toFloat()) {
                currentTab.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.toFloat())
                currentTab.requestLayout()
            }
        }
    }

    private fun changeDmgSize(position: Int, positionOffset: Float) {
        slidingScaleTabLayout.post {
            val scale = 1 + maxScale * (1 - positionOffset)
            changTabDmgWidth(position, scale)
            if (position + 1 < slidingScaleTabLayout.getTabCount()) {
                changTabDmgWidth(position + 1, 1 + maxScale * positionOffset)
            }
        }
    }

    private fun changTabDmgWidth(position: Int, scale: Float) {
        val currentTabDmg = slidingScaleTabLayout.getDmgView(position) ?: return
        if (currentTabDmg.drawable == null) {
            return
        }
        val params = currentTabDmg.layoutParams
        val width = (currentTabDmg.maxWidth * scale).toInt()
        if (params.width != width) {
            params.width = width
            currentTabDmg.layoutParams = params
        }
    }
}