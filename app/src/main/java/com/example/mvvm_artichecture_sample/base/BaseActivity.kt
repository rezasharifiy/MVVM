package com.example.mvvm_artichecture_sample.base

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

abstract class BaseActivity : AppCompatActivity() {
    protected var currView: View? = null
    protected var currLayoutName: String? = null
    private val isSendData = false

    private fun manageFullScreen(activity: AppCompatActivity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = activity.window
            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            // finally change the color
        }
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        manageFullScreen(this)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    fun setLayoutView(layoutID: Int) {
        val inflater = getSystemService(
                Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        currView = inflater.inflate(layoutID, null)
        currLayoutName = resources.getResourceEntryName(layoutID)
        setContentView(currView)
    }

    fun setLayoutView(layoutID: Int, viewName: String) {
        setLayoutView(layoutID)
    }

    override fun onResume() {
        super.onResume()
    }

    protected fun onResumePrayTime() {
        super.onResume()
    }

}
