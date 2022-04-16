package com.example.syncronossweatherapp.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.syncronossweatherapp.model.WeatherResponse
import com.example.syncronossweatherapp.repository.*
import javax.inject.Inject
import javax.inject.Provider

class WeatherWork(
    appContext: Context,
    workerParams: WorkerParameters,
    private val dataStorageRepository: IDataStorageRepository,
    private val locationRepository: ILocationRepository,
    private val weatherRepository: IWeatherRepository
) : CoroutineWorker(appContext, workerParams) {

    companion object {
        const val WORK_NAME = "WeatherWork"
    }

    override suspend fun doWork(): Result {
        try {
            val location = locationRepository.getLocation()

            val weather : WeatherResponse? = location?.let { loc ->
                weatherRepository
                    .getWeatherByLocation(loc.latitude.toString(), loc.longitude.toString())
            } ?: run {
                return Result.failure()
            }

            weather?.let { w ->
                dataStorageRepository.updateWeather(w)
            } ?: run {
                return Result.failure()
            }

            return Result.success()
        } catch (e: Throwable) {
            return Result.failure()
        }
    }

    class Factory @Inject constructor(
        private val dataStorageRepository: Provider<IDataStorageRepository>,
        private val locationRepository: Provider<ILocationRepository>,
        private val weatherRepository: Provider<IWeatherRepository>
    ) : IWeatherWork<WeatherWork> {

        override fun create(appContext: Context, params: WorkerParameters): WeatherWork {
            return WeatherWork(
                appContext,
                params,
                dataStorageRepository.get(),
                locationRepository.get(),
                weatherRepository.get()
            )
        }
    }
}