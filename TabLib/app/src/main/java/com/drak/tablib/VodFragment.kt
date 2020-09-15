package com.drak.tablib

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_vod.*

/**
 *  @author BuMingYang
 *  @des
 */
class VodFragment : Fragment() {

    private var mNavName = ""

    companion object {
        fun getInstance(
            navName: String
        ): VodFragment {
            val fragment = VodFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mNavName = navName
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vod, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        vod_title.text = mNavName
    }
}