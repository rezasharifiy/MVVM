package com.example.mvvm_artichecture_sample.data;

import com.example.mvvm_artichecture_sample.data.remote.apimodel.ReposnseModel;

import io.reactivex.Observable;
import retrofit2.Response;

public interface MainRepositoryHandler {
    Observable<Response<ReposnseModel>> getList();
}
