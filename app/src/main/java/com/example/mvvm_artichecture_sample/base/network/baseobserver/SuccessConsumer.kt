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
    override fun accept(response: Response<*>) {
        if (response.isSuccessful) {
            manageSuccess(response)
        } else {
            if (response.errorBody() != null && response.code() < RestCode.SERVER_ERROR_STATUS_CODE) {
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

    private fun manageSuccess(response: Response<*>) {

        try {
            listenerWeakReference.get()!!.onSuccess(response.body()!!, response.code(), requestType)
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
            val apiError = APIError("Unexpected Error", ClientCode.EXCEPTION_CODE)
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
