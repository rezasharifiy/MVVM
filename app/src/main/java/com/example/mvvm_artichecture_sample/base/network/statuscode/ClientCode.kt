package com.example.mvvm_artichecture_sample.base.network.statuscode

object ClientCode {

    const val NO_INTERNET_TIMEOUT_CODE = 700
    const val SERVER_TIMEOUT_CODE = 701

    const val EXCEPTION_CODE = -1


    fun isExceptionError(code: Int): Boolean {
        return code == NO_INTERNET_TIMEOUT_CODE || code == SERVER_TIMEOUT_CODE
    }
}
