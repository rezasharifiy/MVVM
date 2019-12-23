package com.example.mvvm_artichecture_sample.base.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Error {
    @SerializedName("reason")
    @Expose
    var reason: Int? = null
    @SerializedName("message")
    @Expose
    var message: String? = null
}
