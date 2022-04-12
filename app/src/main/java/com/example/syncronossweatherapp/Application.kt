package com.example.syncronossweatherapp

import android.app.Application
import com.example.syncronossweatherapp.di.ApplicationComponent
import com.example.syncronossweatherapp.di.DaggerApplicationComponent

class Application : Application() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerApplicationComponent.factory().create(this)
    }
}