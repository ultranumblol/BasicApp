package com.yupao.app.model.repository

data class OkResponse<out T>(val errorCode: Int, val errorMsg: String, val data: T)