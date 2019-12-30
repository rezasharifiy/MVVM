package com.example.mvvm_artichecture_sample.data

import com.example.mvvm_artichecture_sample.base.BaseRepository
import com.example.mvvm_artichecture_sample.base.network.Output
import com.example.mvvm_artichecture_sample.base.network.RetrofitClient
import com.example.mvvm_artichecture_sample.data.remote.ApiInterface
import com.example.mvvm_artichecture_sample.data.remote.apimodel.ResponseModel

class MainRepository : BaseRepository<ApiInterface>(), MainRepositoryHandler {


    override suspend fun countries(requestType: String): Output<ResponseModel> {
        return safeApiCall(
                call = { client.getCountryAsync().await() },
                requestType = requestType
        )
    }

    override val client: ApiInterface
        get() = RetrofitClient.getInstance().createRequest(ApiInterface::class.java)

}
