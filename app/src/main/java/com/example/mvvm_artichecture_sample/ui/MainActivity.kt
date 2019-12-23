package com.example.mvvm_artichecture_sample.ui

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import com.example.mvvm_artichecture_sample.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragment = MainFragment.instance
            supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, fragment, MainFragment.TAG).commit()
        }
    }
}
