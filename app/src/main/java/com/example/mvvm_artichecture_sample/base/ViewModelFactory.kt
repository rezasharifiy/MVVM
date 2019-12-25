package com.example.mvvm_artichecture_sample.base

import android.app.Activity
import android.app.Application

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.example.mvvm_artichecture_sample.data.MainRepository
import com.example.mvvm_artichecture_sample.ui.MainViewModel

class ViewModelFactory : ViewModelProvider.Factory {
    private var mApplication: Application? = null
    private val mRepository: BaseRepository<*>? = null


    constructor(application: Application) {
        mApplication = application
    }

    constructor(fragment: Fragment) {
        mApplication = checkApplication(checkActivity(fragment))
    }


    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {

            return MainViewModel(MainRepository(), mApplication!!) as T

        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    private fun checkApplication(activity: Activity): Application {
        return activity.application
                ?: throw IllegalStateException("Your activity/fragment is not yet attached to "
                        + "Application. You can't request ViewModelsWithStateFactory "
                        + "before onCreate call.")
    }

    private fun checkActivity(fragment: Fragment): Activity {
        return fragment.activity
                ?: throw IllegalStateException("Can't create ViewModelsWithStateFactory" + " for detached fragment")
    }

}
