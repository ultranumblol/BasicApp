package com.yupao.app.model.api

import okhttp3.OkHttpClient

object AppClient : BaseRetrofitClient() {

    val service by lazy { getService(ApiService::class.java, ApiService.BASE_URL) }

    override fun handleBuilder(builder: OkHttpClient.Builder) {

    }
}