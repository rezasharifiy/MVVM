package com.example.mvvm_artichecture_sample.base.network.base;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseRetrofitClient {

    public <T> T createRequest(final Class<T> tClass) {

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(getBaseUrl())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build();
        return retrofit.create(tClass);
    }

    protected abstract OkHttpClient getClient();

    protected abstract String getBaseUrl();

}
