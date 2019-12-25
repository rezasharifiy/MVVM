package com.example.mvvm_artichecture_sample.base.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class APIError {

    @SerializedName("code")
    @Expose
    var code: Int = 0
    @SerializedName("result")
    @Expose
    var message: String? = null
    @SerializedName("Error")
    @Expose
    var error: Error? = null

    constructor(message: String, code: Int) {
        this.message = message
        this.code = code
    }

    constructor(field: Int?, message: String) {
        this.message = message
    }

    constructor() {}

    companion object {

        val FIELD = "field"
        val MESSAGE = "message"
        val EXCEPTION = "exception"
    }
}
