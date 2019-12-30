package com.example.mvvm_artichecture_sample.data.remote

import com.example.mvvm_artichecture_sample.data.remote.apimodel.ResponseModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("countries")
    suspend fun getCountryList(): Response<ResponseModel>

    @GET("countries")
    fun getCountryAsync(): Deferred<Response<ResponseModel>>

}
