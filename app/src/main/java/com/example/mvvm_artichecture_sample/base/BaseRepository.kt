package com.example.mvvm_artichecture_sample.base

import com.example.mvvm_artichecture_sample.base.network.Output
import com.example.mvvm_artichecture_sample.base.network.ResponseGenerator
import retrofit2.Response
import java.io.IOException


abstract class BaseRepository<R> {
    protected abstract val client: R

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, requestType: String): Output<T> {
        return ResponseGenerator<T>(call, requestType).callApi()
    }
}
