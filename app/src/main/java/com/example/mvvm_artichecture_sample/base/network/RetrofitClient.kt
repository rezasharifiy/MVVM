package com.example.mvvm_artichecture_sample.base.network

import com.example.mvvm_artichecture_sample.base.network.base.BaseRetrofitClient

import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient

class RetrofitClient private constructor() : BaseRetrofitClient() {

    override val client: OkHttpClient
        get() = okHttpClient

    private val okHttpClient: OkHttpClient
        get() {


            val builder = OkHttpClient.Builder()
                    .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(false)
            return builder.build()
        }

    override val baseUrl: String
        get() = BASE_URL

    companion object {
        private val WRITE_TIME_OUT: Long = 10
        private val READ_TIME_OUT: Long = 20
        val BASE_URL = "https://api.printful.com/"
        private var instance: RetrofitClient? = null

        fun getInstance(): RetrofitClient {
            if (instance == null) {
                instance = RetrofitClient()
            }
            return instance!!
        }
    }
}
