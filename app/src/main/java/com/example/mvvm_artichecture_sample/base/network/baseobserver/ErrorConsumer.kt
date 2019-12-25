package com.example.mvvm_artichecture_sample.base.network.baseobserver

import com.example.mvvm_artichecture_sample.base.network.model.APIError
import com.example.mvvm_artichecture_sample.base.network.statuscode.ClientCode

import java.lang.ref.WeakReference
import java.net.SocketTimeoutException
import java.net.UnknownHostException

import io.reactivex.functions.Consumer

class ErrorConsumer(listener: WebServiceListener, private val requestType: String) : Consumer<Throwable> {

    private val listenerWeakReference: WeakReference<WebServiceListener>

    init {
        listenerWeakReference = WeakReference(listener)
    }

    @Throws(Exception::class)
    override fun accept(throwable: Throwable) {
        val apiError = APIError()
        val code: Int
        if (throwable is UnknownHostException || throwable is SocketTimeoutException) {
            apiError.code = ClientCode.NO_INTERNET_TIMEOUT_CODE
            code = ClientCode.NO_INTERNET_TIMEOUT_CODE
        } else {
            apiError.code = ClientCode.SERVER_TIMEOUT_CODE
            code = ClientCode.SERVER_TIMEOUT_CODE
        }
        apiError.message = "Unexpected Error"
        manageOnError(apiError, code, requestType)
    }

    private fun manageOnError(apiError: APIError, statusCode: Int, requestType: String) {
        try {
            listenerWeakReference.get()!!.onError(apiError, statusCode, requestType)
        } catch (e: Exception) {
            manageInternalException(e)
        }

    }

    private fun manageInternalException(ex: Exception) {
        ex.printStackTrace()
        if (listenerWeakReference.get() != null) {
            try {
                val apiError = APIError("Unexpected Error", ClientCode.EXCEPTION_CODE)
                listenerWeakReference.get()!!.onError(apiError, ClientCode.EXCEPTION_CODE, requestType)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}
