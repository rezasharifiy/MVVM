package com.example.mvvm_artichecture_sample.base.network;

import com.example.mvvm_artichecture_sample.base.network.base.BaseRetrofitClient;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class RetrofitClient extends BaseRetrofitClient {
    private static final long WRITE_TIME_OUT = 10;
    private static final long READ_TIME_OUT = 20;
    public static final String BASE_URL = "https://api.printful.com/";
    private static RetrofitClient instance = null;


    private RetrofitClient() {

    }

    public static RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    @Override
    protected OkHttpClient getClient() {
        return getOkHttpClient();
    }

    private OkHttpClient getOkHttpClient() {


        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false);
        return builder.build();
    }

    @Override
    protected String getBaseUrl() {
        return BASE_URL;
    }
}
