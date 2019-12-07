package com.yupao.app.ui.main


import androidx.lifecycle.viewModelScope
import com.cc.ktx_ext_base.base.BaseViewModel
import com.cc.ktx_ext_base.ext.logd
import com.cc.ktx_ext_base.ext.showLog
import com.yupao.app.model.api.Result
import com.yupao.app.model.bean.MainList
import com.yupao.app.model.repository.HomeRepsitory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainViewModel : BaseViewModel() {

    private val homeRepsitory by lazy { HomeRepsitory() }

    var datas = arrayListOf<MainList>()

    fun setdata(){

    }

    fun getHomeData(){
        viewModelScope.launch (Dispatchers.Default){
           val result =  homeRepsitory.getHomeData()
            withContext(Dispatchers.Main){
                result.toString().logd(tag = "wgz")
            if (result is Result.Success){
                    result.toString().logd(tag = "wgz")
            }
            }
        }
    }

}
