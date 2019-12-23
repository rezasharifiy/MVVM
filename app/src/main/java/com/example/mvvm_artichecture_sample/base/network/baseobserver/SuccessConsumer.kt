package com.example.mvvm_artichecture_sample.base.network.baseobserver

import com.example.mvvm_artichecture_sample.base.network.model.APIError
import com.example.mvvm_artichecture_sample.base.network.statuscode.ClientCode
import com.example.mvvm_artichecture_sample.base.network.statuscode.RestCode
import com.google.gson.Gson

import java.lang.ref.WeakReference

import io.reactivex.functions.Consumer
import okhttp3.ResponseBody
import retrofit2.Response

class SuccessConsumer(listener: WebServiceListener, private val requestType: String) : Consumer<Response<*>> {

    private val listenerWeakReference: WeakReference<WebServiceListener>

    init {
        listenerWeakReference = WeakReference(listener)
    }

    @Throws(Exception::class)
    override fun accept(tResponse: Response<*>) {
        if (tResponse.isSuccessful) {
            manageSuccess(tResponse)
        } else {
            if (tResponse.errorBody() != null && tResponse.code() < RestCode.SERVER_ERROR_STATUS_CODE) {
                var apiErrors: APIError? = getErrorMessage(tResponse.errorBody())
                if (apiErrors != null) {
                    manageOnError(apiErrors, tResponse.code(), requestType)
                } else {
                    apiErrors = APIError("Unexpected error", tResponse.code())
                    manageOnError(apiErrors, tResponse.code(), requestType)
                }

            } else {
                val apiError = APIError("Unexpected error", tResponse.code())
                manageOnError(apiError, tResponse.code(), requestType)
            }
        }
    }

    private fun manageSuccess(tResponse: Response<*>) {

        try {
            listenerWeakReference.get()!!.onSuccess(tResponse.body()!!, tResponse.code(), requestType)
        } catch (e: Exception) {
            manageInternalException(e)
        }
    }

    private fun manageOnError(apiError: APIError, statusCode: Int, requestType: String) {
        var apiError = apiError
        try {
            listenerWeakReference.get()!!.onError(apiError, statusCode, requestType)
        } catch (e: Exception) {
            manageInternalException(e)
        }

    }

    private fun manageInternalException(ex: Exception) {
        ex.printStackTrace()
        try {
            val apiError = APIError("Unexpected error", ClientCode.EXCEPTION_CODE)
            listenerWeakReference.get()!!.onError(apiError, ClientCode.EXCEPTION_CODE, requestType)
        } catch (e: Exception) {
            e.printStackTrace()
        }
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
