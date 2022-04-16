package com.example.syncronossweatherapp

import android.app.Application
import androidx.work.Configuration
import com.example.syncronossweatherapp.di.ApplicationComponent
import com.example.syncronossweatherapp.di.DaggerApplicationComponent
import com.example.syncronossweatherapp.di.WorkerInjectorFactory
import javax.inject.Inject

class WeatherApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: WorkerInjectorFactory

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerApplicationComponent.builder().application(this).build()
        appComponent.inject(this)
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.INFO)
            .setWorkerFactory(workerFactory)
            .build()

    }
}