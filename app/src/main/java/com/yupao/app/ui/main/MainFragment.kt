package com.yupao.app.ui.main

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.cc.ktx_ext_base.base.BaseVMFragment
import com.chad.library.adapter.base.BaseQuickAdapter
import com.yupao.app.R
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : BaseVMFragment<MainViewModel>() {
    override fun getLayoutResId(): Int = R.layout.main_fragment
    override fun providerVMClass(): Class<MainViewModel>? = MainViewModel::class.java
    private val mainadapter by lazy { MainActivityAdapter() }


    override fun initView() {
        initpager()

        mainRefreshLayout.setOnRefreshListener {
            refresh()
        }
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
                        message(text = mainadapter.getItem(pos)!!.detail)
                    }
                }
            }
        }

    override fun initData() {
        refresh()
    }

    private fun refresh() {
        mainadapter.setEnableLoadMore(false)
        mViewModel.getHomeData()
    }

    override fun startObserve() {
        super.startObserve()
        mViewModel.apply {
            uiState.observe(this@MainFragment, Observer {
                mainRefreshLayout.isRefreshing = it.showLoading

                it.showSuccess?.let { data ->
                    mainadapter.run {
//                        if (it.isRefresh) replaceData(data.information_list)
//                        else addData(data.information_list)
                        replaceData(data.information_list)
                        setEnableLoadMore(true)
                        loadMoreComplete()
                    }
                }

                it.showError?.let { message ->
                    MaterialDialog(context!!).show {
                        lifecycleOwner(this@MainFragment)
                        message(text = message)
                    }
                }
            })
        }

    }

}
