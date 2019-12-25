package com.example.mvvm_artichecture_sample.data.remote.apimodel

data class Country(
    val code: String,
    val name: String,
    val states: List<State>
)