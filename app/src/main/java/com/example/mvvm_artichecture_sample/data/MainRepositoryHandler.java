package com.example.mvvm_artichecture_sample.data;

import com.example.mvvm_artichecture_sample.data.remote.apimodel.CountryModel;

import io.reactivex.Observable;
import retrofit2.Response;

public interface MainRepositoryHandler {
    Observable<Response<CountryModel>> getList();
}
