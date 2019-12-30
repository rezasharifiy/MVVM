package com.example.mvvm_artichecture_sample.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

open class BaseViewModel<R : BaseRepository<*>>(application: Application, repository: R) : AndroidViewModel(application), LifecycleObserver {

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Default
    protected val scope = CoroutineScope(coroutineContext)

    protected var repository: R? = null
    var lifecycle: Lifecycle? = null

    protected val isNetworkConnected: Boolean
        get() = Util.getInstance().isConnection(getApplication())

    init {
        this.repository = repository

    }

    fun setLifeCycle(lifecycle: Lifecycle) {
        this.lifecycle = lifecycle
        addLifecycle(lifecycle)
    }

    private fun addLifecycle(lifecycle: Lifecycle) {
        lifecycle.addObserver(this)
    }

    private fun cancelRequests() = coroutineContext.cancelChildren()

    override fun onCleared() {
        repository = null
        lifecycle = null
        cancelRequests()
        super.onCleared()
    }


    protected fun getString(id: Int): String {
        return getApplication<Application>().getString(id)
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected fun onStartView() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    protected fun onResumeView() {


    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected fun onDestroy() {

    }


    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    protected fun onPauseView() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    protected fun onStopView() {

    }


}
