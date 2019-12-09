package com.yupao.app.model.api


import com.yupao.app.model.bean.HomeData
import com.yupao.app.model.bean.UserBean
import com.yupao.app.model.repository.OkResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    companion object {
        const val BASE_URL = "http://newapp.54xiaoshuo.com"
    }

    @GET("/index/list-data")
    suspend fun getHomeData(): HomeData

    @FormUrlEncoded
    @POST("/member/login")
    suspend fun login(@Field("tel") username : String , @Field("pass") password : String)  : OkResponse<UserBean>




}