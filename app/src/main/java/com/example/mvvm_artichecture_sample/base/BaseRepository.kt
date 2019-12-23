package com.example.mvvm_artichecture_sample.base

import com.example.mvvm_artichecture_sample.base.network.base.BaseRetrofitClient


abstract class BaseRepository<R : BaseRetrofitClient> {
    protected abstract val client: R
}
