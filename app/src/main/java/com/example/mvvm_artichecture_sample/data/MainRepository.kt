package com.example.mvvm_artichecture_sample.data

import com.example.mvvm_artichecture_sample.base.BaseRepository
import com.example.mvvm_artichecture_sample.base.network.RetrofitClient
import com.example.mvvm_artichecture_sample.base.network.base.BaseRetrofitClient
import com.example.mvvm_artichecture_sample.data.remote.ApiInterface
import com.example.mvvm_artichecture_sample.data.remote.apimodel.ReposnseModel

import io.reactivex.Observable
import retrofit2.Response

class MainRepository : BaseRepository<BaseRetrofitClient>(), MainRepositoryHandler {



    override val list: Observable<Response<ReposnseModel>>
        get() = client.createRequest(ApiInterface::class.java).list


    override val client: BaseRetrofitClient
        get() = RetrofitClient.getInstance()

}
