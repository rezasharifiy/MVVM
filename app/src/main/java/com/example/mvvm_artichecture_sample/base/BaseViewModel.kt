package com.example.mvvm_artichecture_sample.base

import android.app.Application

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

open class BaseViewModel<R : BaseRepository<*>>(application: Application, repository: R) : AndroidViewModel(application), LifecycleObserver {

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Default
    protected val scope = CoroutineScope(coroutineContext)

    protected var repository: R? = null
        private set
    val compositeDisposable: CompositeDisposable
    var lifecycle: Lifecycle? = null
        private set

    protected val isNetworkConnected: Boolean
        get() = Util.getInstance().isConnection(getApplication())

    init {
        this.repository = repository
        this.compositeDisposable = CompositeDisposable()

    }

    fun setLifeCycle(lifecycle: Lifecycle) {
        this.lifecycle = lifecycle
        addLifecycle(lifecycle)
    }

    private fun addLifecycle(lifecycle: Lifecycle) {
        lifecycle.addObserver(this)
    }

    fun cancelRequests() = coroutineContext.cancel()

    override fun onCleared() {
//        compositeDisposable.dispose()
        repository = null
        lifecycle = null
        cancelRequests()
        super.onCleared()
    }

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
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
