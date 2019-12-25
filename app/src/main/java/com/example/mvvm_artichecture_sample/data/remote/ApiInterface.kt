package com.example.mvvm_artichecture_sample.data.remote

import com.example.mvvm_artichecture_sample.data.remote.apimodel.ResponsModel
import io.reactivex.Observable
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("countries")
     fun getCountryList(): Observable<Response<ResponsModel>>

    @GET("countries")
     fun getCountreis(): Deferred<Response<ResponsModel>>

}
