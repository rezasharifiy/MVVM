package com.example.mvvm_artichecture_sample.base;

import android.app.Application;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;


public class SaveStateViewModel extends ViewModel {
    public static final String MAIN = "main";
    private final SavedStateHandle state;

    public SaveStateViewModel(Application application, SavedStateHandle savedStateHandle) {
//        super(application);
        state = savedStateHandle;
    }

}
