package com.drak.tab

interface ITabScaleTransformer {
    fun setNormalWidth(position: Int, width: Int, isSelect: Boolean)
    fun onPageScrolled(
        position: Int,
        positionOffset: Float,
        positionOffsetPixels: Int
    )
}