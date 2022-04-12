package com.example.syncronossweatherapp.repository

import com.example.syncronossweatherapp.model.WeatherResponse

interface IDataStorageRepository {
    fun getLastWeather(): WeatherResponse?
    fun updateWeather(lastWeather: WeatherResponse)
}