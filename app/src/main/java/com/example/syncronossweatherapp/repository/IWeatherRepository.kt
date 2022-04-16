package com.example.syncronossweatherapp.repository

import com.example.syncronossweatherapp.model.WeatherResponse

interface IWeatherRepository {
    suspend fun getWeatherByLocation(lat: String, lon: String): WeatherResponse?
}