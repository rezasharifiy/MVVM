package com.example.mvvm_artichecture_sample.data

import com.example.mvvm_artichecture_sample.base.network.Output
import com.example.mvvm_artichecture_sample.data.remote.apimodel.ResponsModel


interface MainRepositoryHandler {
    suspend fun countries(requestType:String): Output<ResponsModel>

}
