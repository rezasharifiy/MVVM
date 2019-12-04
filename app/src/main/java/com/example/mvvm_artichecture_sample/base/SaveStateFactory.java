package com.example.mvvm_artichecture_sample.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AbstractSavedStateVMFactory;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.savedstate.SavedStateRegistryOwner;

public class SaveStateFactory extends AbstractSavedStateVMFactory {
    private final Application mApplication;

    /**
     * Constructs this factory.
     *
     * @param owner       {@link SavedStateRegistryOwner} that will provide restored state for created
     *                    {@link ViewModel ViewModels}
     * @param defaultArgs values from this {@code Bundle} will be used as defaults by
     *                    {@link SavedStateHandle} passed in {@link ViewModel ViewModels}
     *                    if there is no previously saved state
     */

    public SaveStateFactory(Application application, @NonNull SavedStateRegistryOwner owner,
            @Nullable Bundle defaultArgs) {
        super(owner, defaultArgs);
        mApplication = application;
    }

    public SaveStateFactory(Fragment fragment) {
        this(checkApplication(checkActivity(fragment)), fragment, null);

    }

    @NonNull
    @Override
    protected <T extends ViewModel> T create(@NonNull String key, @NonNull Class<T> modelClass, @NonNull SavedStateHandle handle) {
        if (modelClass.isAssignableFrom(SaveStateViewModel.class)) {
            //noinspection unchecked
            return (T) new SaveStateViewModel(mApplication, handle);
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
