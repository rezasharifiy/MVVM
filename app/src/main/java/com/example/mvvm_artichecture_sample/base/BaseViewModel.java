package com.example.mvvm_artichecture_sample.base;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseViewModel<R extends BaseRepository> extends AndroidViewModel implements LifecycleObserver {


    private R repository;
    private CompositeDisposable mCompositeDisposable;
    private Lifecycle mLifecycle;

    public BaseViewModel(Application application, R repository) {
        super(application);
        this.repository = repository;
        this.mCompositeDisposable = new CompositeDisposable();

    }

    public void setLifeCycle(Lifecycle lifecycle) {
        mLifecycle = lifecycle;
        addLifecycle(lifecycle);
    }

    private void addLifecycle(Lifecycle lifecycle) {
        lifecycle.addObserver(this);
    }

    protected R getRepository() {
        return repository;
    }


    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        repository = null;
        mLifecycle = null;
        super.onCleared();
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public void addDisposable(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }

    protected boolean isNetworkConnected() {
        return
                Util.getInstance().isConnection(getApplication());
    }

    protected String getString(int id) {
        return getApplication().getString(id);
    }

    public Lifecycle getLifecycle() {
        return mLifecycle;
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected void onStartView() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    protected void onResumeView() {


    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected void onDestroy() {

    }


    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    protected void onPauseView() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    protected void onStopView() {

    }

}
