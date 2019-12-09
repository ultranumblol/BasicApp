package com.yupao.app.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cc.ktx_ext_base.base.BaseViewModel
import com.yupao.app.model.api.Result
import com.yupao.app.model.bean.User
import com.yupao.app.model.bean.UserBean
import com.yupao.app.model.repository.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel : BaseViewModel() {

    private val _uiState = MutableLiveData<LoginUiModel>()
    private val loginrepository by lazy { LoginResponse() }

    val uiState: LiveData<LoginUiModel>
        get() = _uiState

    val mRegisterUser: MutableLiveData<User> = MutableLiveData()

    fun login(username: String, password: String) {
        viewModelScope.launch(Dispatchers.Default) {
            if (username.isBlank() || password.isBlank()) return@launch
            withContext(Dispatchers.Main) { emitUiState(true) }
            val result = loginrepository.login(username, password)
            withContext(Dispatchers.Main) {
                if (result is Result.Success) {
                    emitUiState(showSuccess = result.data, enableLoginButton = true)
                } else if (result is Result.Error) {
                    emitUiState(showError = result.exception.message, enableLoginButton = true)
                }
            }
        }
    }


    private fun emitUiState(
        showProgress: Boolean = false,
        showError: String? = null,
        showSuccess: UserBean? = null,
        enableLoginButton: Boolean = false,
        needLogin: Boolean = false
    ) {
        val uiModel =
            LoginUiModel(showProgress, showError, showSuccess, enableLoginButton, needLogin)
        _uiState.value = uiModel
    }

    data class LoginUiModel(
        val showProgress: Boolean,
        val showError: String?,
        val showSuccess: UserBean?,
        val enableLoginButton: Boolean,
        val needLogin: Boolean
    )
}