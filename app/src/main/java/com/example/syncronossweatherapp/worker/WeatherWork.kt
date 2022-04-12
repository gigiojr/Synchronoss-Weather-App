package com.example.syncronossweatherapp.worker

import android.content.Context
import android.location.Location
import android.util.Log
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.syncronossweatherapp.repository.*
import javax.inject.Inject
import javax.inject.Provider


class WeatherWork(
    appContext: Context,
    workerParams: WorkerParameters,
    private val weatherRepository: IWeatherRepository,
    private val locationRepository: ILocationRepository) : Worker(appContext, workerParams) {

    companion object {
        const val WORK_NAME = "WeatherWork"
    }

    override fun doWork(): Result {
        Log.i("WeatherWork", "oi lindao!")

        locationRepository.getLocation(object : ILocationCallback {
            override fun onLocation(location: Location) {
                weatherRepository.getWeatherByLocation(
                    location.latitude.toString(),
                    location.longitude.toString())
            }

            override fun onFailure() {
                TODO("Not yet implemented")
            }
        })

        return Result.success()
    }

    class Factory @Inject constructor(
        private val weatherRepository: Provider<IWeatherRepository>,
        private val locationRepository: Provider<ILocationRepository>
    ) : IWeatherWork {
        override fun create(appContext: Context, params: WorkerParameters): ListenableWorker {
            return WeatherWork(
                appContext,
                params,
                weatherRepository.get(),
                locationRepository.get()
            )
        }
    }
}