package com.yupao.app.model.api

import com.cc.ktx_ext_base.ext.logd
import com.yupao.app.model.bean.HomeData
import com.yupao.app.model.repository.FuckResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

import java.io.IOException

open class BaseRepository {

    suspend fun <T : Any> apiCall(call: suspend () -> FuckResponse<T>): FuckResponse<T> {
        return call.invoke()
    }

    suspend fun <T : Any> safeApiCall(
        call: suspend () -> Result<T>,
        errorMessage: String
    ): Result<T> {
        return try {
            call()
        } catch (e: Exception) {
            // An exception was thrown when calling the API so we're converting this to an IOException
            "网络请求错误 ：$errorMessage".logd("wgz")
            Result.Error(IOException(errorMessage, e))
        }
    }

    suspend fun <T : Any> executeResponse(
        response: T, successBlock: (suspend CoroutineScope.() -> Unit)? = null,
        errorBlock: (suspend CoroutineScope.() -> Unit)? = null
    ): Result<T> {    // response.toString() + "jieguo".logd("wgz")
        val type = when (response) {
            is HomeData -> 1
            else -> -1
        }
        return coroutineScope {
            if (type == 1) {
                successBlock?.let { it() }
                Result.Success(response)
            } else {
                errorBlock?.let { it() }
                Result.Error(IOException("网络炸了"))
            }
        }
    }


}