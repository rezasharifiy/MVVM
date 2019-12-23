package com.example.mvvm_artichecture_sample.base.network.baseobserver

import com.example.mvvm_artichecture_sample.base.network.model.APIError

interface WebServiceListener {

    @Throws(Exception::class)
    fun onSuccess(`object`: Any, statusCode: Int, requestType: String)

    @Throws(Exception::class)
    fun onError(apiError: APIError, statusCode: Int, requestType: String)

}
