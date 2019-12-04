package com.example.mvvm_artichecture_sample.data.remote;

import com.example.mvvm_artichecture_sample.data.remote.apimodel.CountryModel;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiInterface {

    @Headers("Content-Type: application/json")
    @GET("countries")
    Observable<Response<CountryModel>> getList();
}
