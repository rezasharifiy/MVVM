package com.example.mvvm_artichecture_sample.data.remote.apimodel

data class ResponseModel(
    val code: Int,
    val extra: List<Any>,
    val result: List<Country>
)