package com.yupao.app.ui.main


import com.cc.ktx_ext_base.base.BaseActivity
import com.yupao.app.R

class MainActivity : BaseActivity() {

    override fun getLayoutResId(): Int =R.layout.main_activity

    override fun initView() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, MainFragment())
            .commitNow()
    }
    override fun initData() {

    }
}
