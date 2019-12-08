package com.yupao.app.ui.main


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cc.ktx_ext_base.base.BaseViewModel
import com.cc.ktx_ext_base.ext.logd
import com.cc.ktx_ext_base.ext.showLog
import com.yupao.app.model.api.AppClient
import com.yupao.app.model.api.Result
import com.yupao.app.model.bean.HomeData
import com.yupao.app.model.bean.MainList
import com.yupao.app.model.repository.HomeRepsitory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainViewModel : BaseViewModel() {

    private val homeRepsitory by lazy { HomeRepsitory() }
    private val _uiState = MutableLiveData<MainFragmentUiModel>()

    val uiState: LiveData<MainFragmentUiModel>
        get() = _uiState


    fun getHomeData() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = homeRepsitory.getHomeData()
            withContext(Dispatchers.Main) {
                if (result is Result.Success) {
                    result.toString().logd(tag = "wgz")
                    emitHomeFragmentUiState(showSuccess = result.data)
                }
            }
        }
    }

    private fun emitHomeFragmentUiState(
        showLoading: Boolean = false,
        showError: String? = null,
        showSuccess: HomeData? = null,
        showEnd: Boolean = false,
        isRefresh: Boolean = false,
        needLogin: Boolean? = null
    ) {
        val uiModel =
            MainFragmentUiModel(showLoading, showError, showSuccess, showEnd, isRefresh, needLogin)
        _uiState.value = uiModel
    }

    data class MainFragmentUiModel(
        val showLoading: Boolean,
        val showError: String?,
        val showSuccess: HomeData?,
        val showEnd: Boolean, // 加载更多
        val isRefresh: Boolean, // 刷新
        val needLogin: Boolean? = null
    )


}
