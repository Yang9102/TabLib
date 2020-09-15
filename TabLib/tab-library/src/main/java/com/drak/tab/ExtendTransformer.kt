package com.drak.tab

import android.view.View
import androidx.viewpager.widget.ViewPager.PageTransformer
import java.util.*

/**
 * @author dark
 */
class ExtendTransformer : PageTransformer {
    private val transformers: ArrayList<IViewPagerTransformer> = ArrayList()

    fun addViewPagerTransformer(transformer: IViewPagerTransformer) {
        if (!transformers!!.contains(transformer)) {
            transformers.add(transformer)
        }
    }

    fun removeViewPagerTransformer(transformer: IViewPagerTransformer?) {
        transformers!!.remove(transformer)
    }

    fun getTransformers(): List<IViewPagerTransformer>? {
        return transformers
    }

    fun setTransformers(transformers: List<IViewPagerTransformer>) {
        this.transformers!!.addAll(transformers)
    }

    override fun transformPage(view: View, position: Float) {
        // 回调设置的页面切换效果设置
        if (transformers != null && transformers.size > 0) {
            for (transformer in transformers) {
                transformer.transformPage(view, position)
            }
        }
    }
}