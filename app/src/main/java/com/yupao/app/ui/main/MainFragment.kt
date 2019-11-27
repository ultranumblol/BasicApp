package com.yupao.app.ui.main


import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.cc.ktx_ext_base.base.BaseVMFragment
import com.chad.library.adapter.base.BaseQuickAdapter
import com.yupao.app.R
import com.yupao.app.adapter.MainActivityAdapter
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : BaseVMFragment<MainViewModel>() {
    override fun getLayoutResId(): Int = R.layout.main_fragment
    override fun providerVMClass(): Class<MainViewModel>? = MainViewModel::class.java
    private val mainadapter by lazy { MainActivityAdapter() }


    override fun initView() {

        initRv()
        ViewCompat.setOnApplyWindowInsetsListener(
            view!!
        ) { _: View?, insetsCompat: WindowInsetsCompat ->
            main_collapsingtoolbarlayout
                .setPadding(0, insetsCompat.systemWindowInsetTop, 0, 0)
            insetsCompat
        }
    }

    private fun initRv() {
        rv_main.run {
            layoutManager = LinearLayoutManager(activity)
        }
        mainadapter.run {
            rv_main.adapter = mainadapter
        }
    }

    private val onItemChildClickListener =
        BaseQuickAdapter.OnItemChildClickListener { _, view, _ ->
            when (view.id) {
                R.id.id_main_name -> {

                }
            }
        }

    override fun initData() {
        refresh()
    }

    private fun refresh() {
        mainadapter.setEnableLoadMore(false)
        mViewModel.setdata()
        mainadapter.addData(mViewModel.datas)
    }

}
