package com.example.mvvm_artichecture_sample.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mvvm_artichecture_sample.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            MainFragment fragment = MainFragment.getInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_container, fragment, MainFragment.TAG).commit();
        }
    }
}
