package com.yupao.app.ui.main

import com.yupao.app.BR
import com.yupao.app.R
import com.yupao.app.adapter.BaseBindAdapter
import com.yupao.app.model.bean.HomeData
import com.yupao.app.model.bean.MainList

class MainActivityAdapter(layoutResId: Int = R.layout.item_main_activity) :
    BaseBindAdapter<HomeData.Information>(layoutResId, BR.informantion) {

    override fun convert(helper: BindViewHolder, item: HomeData.Information) {
        super.convert(helper, item)
        helper.addOnClickListener(R.id.ll_root)
    }
}