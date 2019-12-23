package com.example.mvvm_artichecture_sample.data.remote.apimodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CountryModel {

    @SerializedName("code")
    @Expose
    var code: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("states")
    @Expose
    var states: Any? = null
}
