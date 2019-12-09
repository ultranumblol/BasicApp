package com.yupao.app.model.repository

import com.cc.ktx_ext_base.ext.logd
import com.yupao.app.model.api.AppClient
import com.yupao.app.model.api.BaseRepository
import com.yupao.app.model.api.Result
import com.yupao.app.model.bean.UserBean
import com.yupao.app.util.Preference

class LoginResponse : BaseRepository() {
    private var isLogin by Preference(Preference.IS_LOGIN, false)

    suspend fun login(username: String, password: String): Result<UserBean> {

        return safeApiCall(call ={requestLogin(username, password)}, errorMessage = "登录失败")
    }


    private suspend fun requestLogin(username: String, password: String): Result<UserBean> {
        val respose = AppClient.service.login(username, password)

        return executeResponse(respose, {
            respose.toString().logd("wgz")
            isLogin = true
        })

    }

}