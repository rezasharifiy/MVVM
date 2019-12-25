package com.example.mvvm_artichecture_sample.data

import com.example.mvvm_artichecture_sample.base.BaseRepository
import com.example.mvvm_artichecture_sample.base.network.Output
import com.example.mvvm_artichecture_sample.base.network.RetrofitClient
import com.example.mvvm_artichecture_sample.data.remote.ApiInterface
import com.example.mvvm_artichecture_sample.data.remote.apimodel.ResponsModel

class MainRepository : BaseRepository<ApiInterface>(), MainRepositoryHandler {


    override suspend fun  countries(requestType:String): Output<ResponsModel> {
        return safeApiCall(
                call = { client.getCountreis().await() },
                requestType = requestType
        )
    }
//    override suspend fun countries(): Deferred<Response<ReposnseModel>> {
//        return client.createRequest(ApiInterface::class.java)
//                .getCountreis()
//    }
//
//
//    override suspend fun countryWithRx(): Observable<Response<ReposnseModel>> {
//        return client.createRequest(ApiInterface::class.java)
//                .getCountryList()
//    }


//    suspend fun override countryWithRx: Call<ReposnseModel>
//    get() = client.createRequest(ApiInterface::class.java)
//                .getCurrentWeather()
//                .execute()
//                .body()


    override val client: ApiInterface
        get() = RetrofitClient.getInstance().createRequest(ApiInterface::class.java)

}
