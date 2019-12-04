package com.example.mvvm_artichecture_sample.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import io.reactivex.disposables.Disposable;

public abstract class BaseFragment<V extends BaseViewModel> extends Fragment {

    protected View currView;
    protected String currLayoutName;
    protected Context mContext;
    protected boolean isActive;
    private String mClassName = null;
    protected V mViewModel;

    protected abstract V getViewModel();

    /**
     * @return layout resource id
     */
    protected abstract
    @LayoutRes
    int getLayoutId();


    /**
     * This method called after initialize view
     */
    protected abstract void setupView();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = getViewModel();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (currView == null) {
            setLayoutView(getLayoutId(), inflater, container, "");
            setupView();
        }
        return currView;
    }


    public void setLayoutView(int layoutId, LayoutInflater inflater, ViewGroup container) {
        currView = inflater.inflate(layoutId, container, false);

        currLayoutName = getResources().getResourceEntryName(layoutId);
        mClassName = currLayoutName;
        mContext = getContext();
    }

    public void setLayoutView(int layoutId, LayoutInflater inflater, ViewGroup container,
            String className) {
        setLayoutView(layoutId, inflater, container);

        mClassName = className;
    }

    public void addDisposable(Disposable disposable) {
        mViewModel.getCompositeDisposable().add(disposable);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getViewLifecycleOwnerLiveData().removeObservers(this);

    }

    protected void back() {
        if (getActivity() != null) {
            getActivity().onBackPressed();
        }
    }

}
