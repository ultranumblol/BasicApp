package com.yupao.app.ui.main


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cc.ktx_ext_base.base.BaseViewModel
import com.cc.ktx_ext_base.ext.logd
import com.yupao.app.model.api.AppClient
import com.yupao.app.model.api.Result
import com.yupao.app.model.bean.HomeData
import com.yupao.app.model.bean.MainList
import com.yupao.app.model.repository.HomeRepsitory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainViewModel : BaseViewModel() {

    sealed class MainFragmentType {
        object MainFragment : MainFragmentType()
        object SecondFragment : MainFragmentType()
        object ThirdFragment : MainFragmentType()
        object NO4Fragment : MainFragmentType()
    }

    private val homeRepsitory by lazy { HomeRepsitory() }
    private val _uiState = MutableLiveData<MainFragmentUiModel>()

    val uiState: LiveData<MainFragmentUiModel>
        get() = _uiState


    fun getHomeData()  = getDataList(MainFragmentType.MainFragment)


    private fun getDataList(fragmentType: MainFragmentType) {
        viewModelScope.launch(Dispatchers.Default) {
            withContext(Dispatchers.Main) { emitHomeFragmentUiState(true) }
            val result = when (fragmentType) {

                MainFragmentType.MainFragment -> homeRepsitory.getHomeData()
                MainFragmentType.SecondFragment -> homeRepsitory.getHomeData()
                MainFragmentType.ThirdFragment -> homeRepsitory.getHomeData()
                MainFragmentType.NO4Fragment -> homeRepsitory.getHomeData()
            }
            withContext(Dispatchers.Main){
                if (result is Result.Success) {
                    result.toString().logd(tag = "wgz")
                    emitHomeFragmentUiState(showSuccess = result.data,showLoading = false)
                }
                else if (result is Result.Error){
                    emitHomeFragmentUiState(showLoading = false, showError = result.exception.message)
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
