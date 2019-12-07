package com.yupao.app.model.api

import com.yupao.app.model.bean.HomeData
import com.yupao.app.model.repository.FuckResponse
import retrofit2.http.GET

interface ApiService{
    companion object {
        const val BASE_URL = "http://newapp.54xiaoshuo.com"
    }

@GET("/index/list-data")
    suspend fun getHomeData() : FuckResponse<HomeData>

}