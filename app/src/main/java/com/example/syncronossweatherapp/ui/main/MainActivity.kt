package com.example.syncronossweatherapp.ui.main

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.work.*
import com.example.syncronossweatherapp.Application
import com.example.syncronossweatherapp.R
import com.example.syncronossweatherapp.di.MainComponent
import com.example.syncronossweatherapp.worker.WeatherWork
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    companion object{
        const val PERMISSION_REQUEST_CODE = 10
    }

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

        ActivityCompat.requestPermissions(this, arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION),
            PERMISSION_REQUEST_CODE)

        startWorker()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    startWorker()
                } else {
                    finish()
                }
                return
            }

            else -> {
                // Nothing to do here
            }
        }
    }

    private fun startWorker() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .build()

        val weatherWork =
            PeriodicWorkRequestBuilder<WeatherWork>(15, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            WeatherWork.WORK_NAME,
            ExistingPeriodicWorkPolicy.REPLACE,
            weatherWork)
    }
}