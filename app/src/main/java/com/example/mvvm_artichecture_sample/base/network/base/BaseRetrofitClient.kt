package com.example.mvvm_artichecture_sample.base.network.base

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

abstract class BaseRetrofitClient {

    protected abstract val client: OkHttpClient

    protected abstract val baseUrl: String

    fun <T> createRequest(tClass: Class<T>): T {
        val retrofit = Retrofit.Builder().baseUrl(baseUrl)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(MoshiConverterFactory.create())
                .client(client)
                .build()
        return retrofit.create(tClass)
    }

}
