package com.yupao.app.ui.main

import android.graphics.Color
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cc.ktx_ext_base.base.BaseActivity
import com.yupao.app.R
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : BaseActivity() {
    private val fragmentList = arrayListOf<Fragment>()
    private val testfragment by lazy { MainFragment() }
    private val testfragment2 by lazy { MainFragment() }
    private val testfragment3 by lazy { MainFragment() }
    private val testfragment4 by lazy { MainFragment() }

    override fun getLayoutResId(): Int = R.layout.main_activity

    init {
        fragmentList.add(testfragment)
        fragmentList.add(testfragment2)
        fragmentList.add(testfragment3)
        fragmentList.add(testfragment4)
    }


    override fun initView() {
        main_toolbar.apply {
            setTitleTextColor(Color.WHITE)
            title = "测试"
        }
        viewPager.apply {
            currentItem = 0
            offscreenPageLimit = 4
            isUserInputEnabled = false
        }

        viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = fragmentList.size
            override fun createFragment(position: Int): Fragment = fragmentList[position]
        }
        botton_nv.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.botton_nv1 -> viewPager.currentItem = 0
                R.id.botton_nv2 -> viewPager.currentItem = 1
                R.id.botton_nv3 -> viewPager.currentItem = 2
                R.id.botton_nv4 -> viewPager.currentItem = 3
            }
            true
        }

    }

    override fun initData() {

    }
}
