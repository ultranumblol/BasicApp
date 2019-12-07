package com.yupao.app.ui.main

import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.cc.ktx_ext_base.base.BaseVMFragment
import com.chad.library.adapter.base.BaseQuickAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yupao.app.R
import com.yupao.app.adapter.MainActivityAdapter
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : BaseVMFragment<MainViewModel>() {
    override fun getLayoutResId(): Int = R.layout.main_fragment
    override fun providerVMClass(): Class<MainViewModel>? = MainViewModel::class.java
    private val mainadapter by lazy { MainActivityAdapter() }


    override fun initView() {
        initpager()

    }

    private fun initpager() {
        rv_main.run {
            layoutManager = LinearLayoutManager(activity)
        }
        mainadapter.run {
            rv_main.adapter = mainadapter
            onItemChildClickListener = this@MainFragment.onItemChildClickListener
        }
    }

    private val onItemChildClickListener =
        BaseQuickAdapter.OnItemChildClickListener { _, view, pos ->
            when (view.id) {
                R.id.ll_root -> {
                    MaterialDialog(context!!).show {
                        lifecycleOwner(this@MainFragment)
                        message(text =mainadapter.getItem(pos)!!.name)
                    }
                }
            }
        }

    override fun initData() {
        mViewModel.getHomeData()
        refresh()
    }

    private fun refresh() {
        mainadapter.setEnableLoadMore(false)
        mViewModel.setdata()
        mainadapter.addData(mViewModel.datas)
    }

    override fun startObserve() {
        super.startObserve()
        mViewModel.apply {



        }

    }

}
