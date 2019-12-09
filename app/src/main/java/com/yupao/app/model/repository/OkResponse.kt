package com.yupao.app.model.repository

data class OkResponse<out T>(val errcode: String, val errmsg: String, val result: T)