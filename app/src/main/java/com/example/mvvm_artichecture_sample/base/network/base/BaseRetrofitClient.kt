package com.example.mvvm_artichecture_sample.base.network.base

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseRetrofitClient {

    protected abstract val client: OkHttpClient

    protected abstract val baseUrl: String

    fun <T> createRequest(tClass: Class<T>): T {

        val retrofit = Retrofit.Builder().baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        return retrofit.create(tClass)
    }

}
