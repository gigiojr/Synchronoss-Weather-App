package com.example.syncronossweatherapp.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.syncronossweatherapp.repository.IDataStorageRepository
import com.example.syncronossweatherapp.repository.ILocationRepository
import com.example.syncronossweatherapp.repository.IWeatherRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val dataStorageRepository: IDataStorageRepository,
    private val locationRepository: ILocationRepository,
    private val weatherRepository: IWeatherRepository
) : ViewModel() {

    fun getWeather() {
//        weatherRepository.getWeatherByLocation("53.325141", "-6.272325")
        val teste = dataStorageRepository.getLastWeather()
    }
}