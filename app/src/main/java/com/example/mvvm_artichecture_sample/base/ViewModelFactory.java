package com.example.mvvm_artichecture_sample.base;

import android.app.Activity;
import android.app.Application;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvm_artichecture_sample.data.MainRepository;
import com.example.mvvm_artichecture_sample.ui.MainViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private BaseRepository mRepository;


    public ViewModelFactory(Application application) {
        mApplication = application;
    }

    public ViewModelFactory(Fragment fragment) {
        this(checkApplication(checkActivity(fragment)));
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            //noinspection unchecked
            return (T) new MainViewModel(new MainRepository(), mApplication);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }

    private static Application checkApplication(Activity activity) {
        Application application = activity.getApplication();
        if (application == null) {
            throw new IllegalStateException("Your activity/fragment is not yet attached to "
                    + "Application. You can't request ViewModelsWithStateFactory "
                    + "before onCreate call.");
        }
        return application;
    }

    private static Activity checkActivity(Fragment fragment) {
        Activity activity = fragment.getActivity();
        if (activity == null) {
            throw new IllegalStateException("Can't create ViewModelsWithStateFactory"
                    + " for detached fragment");
        }
        return activity;
    }

}
