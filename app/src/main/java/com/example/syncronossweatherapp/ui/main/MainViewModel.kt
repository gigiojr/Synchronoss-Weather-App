package com.example.syncronossweatherapp.ui.main

import androidx.lifecycle.ViewModel
import com.example.syncronossweatherapp.repository.ILocationRepository
import com.example.syncronossweatherapp.repository.IWeatherRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    val weatherRepository: IWeatherRepository,
    val locationRepository: ILocationRepository
) : ViewModel() {

    fun getWeather() {
        weatherRepository.getWeatherByLocation("53.325141", "-6.272325")
    }
}