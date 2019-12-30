package com.example.mvvm_artichecture_sample.data

import com.example.mvvm_artichecture_sample.base.network.Output
import com.example.mvvm_artichecture_sample.data.remote.apimodel.ResponseModel


interface MainRepositoryHandler {
    suspend fun countries(requestType:String): Output<ResponseModel>

}
