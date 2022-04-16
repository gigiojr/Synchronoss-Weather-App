package com.example.syncronossweatherapp.repository

import com.example.syncronossweatherapp.model.WeatherResponse

interface IDataStorageRepository {
    suspend fun getLastWeather(): WeatherResponse?
    suspend fun updateWeather(lastWeather: WeatherResponse)
}