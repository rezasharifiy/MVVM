package com.example.mvvm_artichecture_sample.base.network

import com.example.mvvm_artichecture_sample.base.network.model.APIError
import com.example.mvvm_artichecture_sample.base.network.statuscode.ClientCode
import com.example.mvvm_artichecture_sample.base.network.statuscode.RestCode
import com.google.gson.Gson
import okhttp3.ResponseBody
import java.net.SocketTimeoutException
import java.net.UnknownHostException


open class ResponseGenerator<T : Any>(private val call: suspend () -> retrofit2.Response<T>, private val requestType: String) {


    public suspend fun callApi(): Output<T> {
        return try {
            val response = call.invoke()
            manageResponse(response)
        } catch (e: java.lang.Exception) {
            val apiErrors = APIError("Unexpected Error", ClientCode.EXCEPTION_CODE)
            manageOnError(apiErrors, ClientCode.EXCEPTION_CODE, requestType)
        }

    }

    private fun manageResponse(response: retrofit2.Response<T>): Output<T> {

        if (response.isSuccessful) {
            return manageSuccess(response)
        } else {
            return if (response.errorBody() != null && response.code() < RestCode.SERVER_ERROR_STATUS_CODE) {

                var apiErrors: APIError? = getErrorMessage(response.errorBody())

                if (apiErrors != null) {
                    manageOnError(apiErrors, response.code(), requestType)
                } else {
                    apiErrors = APIError("Unexpected Error", response.code())
                    manageOnError(apiErrors, response.code(), requestType)
                }

            } else {
                val apiError = APIError("Unexpected Error", response.code())
                manageOnError(apiError, response.code(), requestType)
            }
        }
    }


    private fun manageOnError(apiError: APIError, statusCode: Int, requestType: String): Output<T> {
        return try {
            Output.Error(apiError, statusCode, requestType)

        } catch (e: Exception) {
            manageInternalException(e)
        }

    }

    private fun manageSuccess(response: retrofit2.Response<T>): Output<T> {
        return try {
            Output.Success<T>(response.body()!!, response.code(), requestType)
        } catch (e: Exception) {
            manageInternalException(e)
        }
    }

    private fun manageInternalException(ex: Exception): Output<T> {
        ex.printStackTrace()
        val apiError = APIError("Unexpected Error", ClientCode.EXCEPTION_CODE)
        return Output.Error(apiError, ClientCode.EXCEPTION_CODE, requestType)
    }

    private fun getErrorMessage(responseBody: ResponseBody?): APIError {
        var apiErrorList = APIError()
        try {
            apiErrorList = Gson().fromJson(responseBody!!.string(), APIError::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return apiErrorList
    }

}