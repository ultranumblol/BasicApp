package com.yupao.app.adapter

import com.yupao.app.BR
import com.yupao.app.R
import com.yupao.app.model.bean.MainList

class MainActivityAdapter(layoutResId: Int = R.layout.item_main_activity) : BaseBindAdapter<MainList>(layoutResId,BR.name) {

    override fun convert(helper: BindViewHolder, item: MainList) {
        super.convert(helper, item)

    }
}