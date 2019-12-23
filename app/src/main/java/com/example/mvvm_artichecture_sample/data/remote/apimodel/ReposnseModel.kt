package com.example.mvvm_artichecture_sample.data.remote.apimodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ReposnseModel {

    @SerializedName("code")
    @Expose
    var code: Int? = null
    @SerializedName("result")
    @Expose
    var countryModel: List<CountryModel>? = null
    @SerializedName("extra")
    @Expose
    var extra: List<Any>? = null
}
