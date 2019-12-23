package com.example.mvvm_artichecture_sample.base.network.statuscode

object RestCode {
    val OK_STATUS_CODE = 200
    val CREATED_STATUS_CODE = 201
    val NO_CONTENT_STATUS_CODE = 204
    val BAD_REQUEST_STATUS_CODE = 400
    val UNAUTHORIZED_STATUS_CODE = 401
    val FORBITTEN_STATUS_CODE = 403
    val NOT_FOUND_STATUS_CODE = 404
    val PRECONDITION_STATUS_CODE = 412
    val FAILD_DEPEDENCY_STATUS_CODE = 424
    val NOT_ACCEPTABLE_STATUS_CODE = 406
    val SERVER_ERROR_STATUS_CODE = 500
    val NETWORK_CONNECT_TIMEOUT_CODE = 599

    fun isInternalServerError(statusCode: Int): Boolean {
        return RestCode.SERVER_ERROR_STATUS_CODE <= statusCode && statusCode <= NETWORK_CONNECT_TIMEOUT_CODE
    }
}
