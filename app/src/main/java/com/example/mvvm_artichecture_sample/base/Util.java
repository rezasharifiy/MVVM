package com.example.mvvm_artichecture_sample.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Util {

    private static Util instance = null;

    public static Util getInstance() {
        if (instance==null){
            instance=new Util();
        }
        return instance;
    }

    public boolean isConnection(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

}
