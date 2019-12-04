package com.example.mvvm_artichecture_sample.data;

import com.example.mvvm_artichecture_sample.base.BaseRepository;
import com.example.mvvm_artichecture_sample.base.network.RetrofitClient;
import com.example.mvvm_artichecture_sample.base.network.base.BaseRetrofitClient;
import com.example.mvvm_artichecture_sample.data.remote.ApiInterface;
import com.example.mvvm_artichecture_sample.data.remote.apimodel.CountryModel;

import io.reactivex.Observable;
import retrofit2.Response;

public class MainRepository extends BaseRepository implements MainRepositoryHandler {
    @Override
    protected BaseRetrofitClient getClient() {
        return null;
    }

    @Override
    public Observable<Response<CountryModel>> getList() {
        return RetrofitClient.getInstance().createRequest(ApiInterface.class).getList();
    }
}
