package com.drak.tablib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //存放 tab 标题
    private val mTabTitleList = mutableListOf("精选", "蜗.现场", "姐姐驾到", "民生热点", "美食", "在线课堂")
    private val mFragmentList = ArrayList<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initTab()
    }

    private fun initTab() {
        mTabTitleList?.forEachIndexed { _, title ->
            mFragmentList.add(VodFragment.getInstance("" + title))
        }
        mViewPager?.adapter =
            BaseFragmentAdapter(supportFragmentManager, mFragmentList, mTabTitleList)

        mTabLayout?.setViewPager(mViewPager, mTabTitleList.toTypedArray())

    }
}