package com.example.mvvm_artichecture_sample.data.remote;

import com.example.mvvm_artichecture_sample.data.remote.apimodel.ReposnseModel;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiInterface {

    @GET("countries")
    Observable<Response<ReposnseModel>> getList();
}
