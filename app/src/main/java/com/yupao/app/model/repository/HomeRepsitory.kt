package com.yupao.app.model.repository

import com.yupao.app.model.api.ApiService
import com.yupao.app.model.api.AppClient
import com.yupao.app.model.api.BaseRepository
import com.yupao.app.model.api.Result
import com.yupao.app.model.bean.HomeData

class HomeRepsitory() : BaseRepository() {


    suspend fun getHomeData(): Result<HomeData> {
        return safeApiCall(call = { requestHomedata() }, errorMessage = "网络错误")

    }


    suspend fun requestHomedata(): Result<HomeData> =
        executeResponse(AppClient.service.getHomeData())
}