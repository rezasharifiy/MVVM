package com.example.mvvm_artichecture_sample.base

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class  Util {

    fun isConnection(mContext: Context): Boolean {
        val connectivityManager = mContext.getSystemService(
                Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null
    }

    companion object {

        private var instance: Util? = null

        fun getInstance(): Util {
            if (instance == null) {
                instance = Util()
            }
            return instance!!
        }
    }

}
