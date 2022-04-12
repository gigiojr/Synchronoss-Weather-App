package com.example.syncronossweatherapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.syncronossweatherapp.Application
import com.example.syncronossweatherapp.R
import com.example.syncronossweatherapp.di.MainComponent

class MainActivity : AppCompatActivity() {

    lateinit var mainComponent: MainComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        mainComponent = (applicationContext as Application).appComponent.mainComponent().create()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}