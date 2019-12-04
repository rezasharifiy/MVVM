package com.example.mvvm_artichecture_sample.ui;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_artichecture_sample.R;
import com.example.mvvm_artichecture_sample.base.BaseFragment;
import com.example.mvvm_artichecture_sample.base.ViewModelFactory;

public class MainFragment extends BaseFragment<MainViewModel> {

    private RecyclerView mRecyclerView;

    @Override
    protected MainViewModel getViewModel() {
        return ViewModelProviders.of(this, getFactory()).get(MainViewModel.class);
    }

    private ViewModelProvider.Factory getFactory() {
        return new ViewModelFactory(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void setupView() {


    }
}
