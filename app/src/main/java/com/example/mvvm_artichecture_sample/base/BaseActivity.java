package com.example.mvvm_artichecture_sample.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public abstract class BaseActivity extends AppCompatActivity {
    protected View currView;
    protected String currLayoutName;
    private boolean isSendData = false;

    private void manageFullScreen(AppCompatActivity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            // finally change the color
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manageFullScreen(this);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public void setLayoutView(int layoutID) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        currView = inflater.inflate(layoutID, null);
        currLayoutName = getResources().getResourceEntryName(layoutID);
        setContentView(currView);
    }

    public void setLayoutView(int layoutID, String viewName) {
        setLayoutView(layoutID);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void onResumePrayTime() {
        super.onResume();
    }

}
