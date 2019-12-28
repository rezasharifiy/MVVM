package com.example.mvvm_artichecture_sample.base.network

import com.example.mvvm_artichecture_sample.base.network.model.APIError


sealed class Output<out T : Any> {

    data class Success<out T : Any>(val response: T, val statusCode: Int, val requestType: String) : Output<T>()

    data class Error(val apiError: APIError, val statusCode: Int, val requestType: String) : Output<Nothing>()

}