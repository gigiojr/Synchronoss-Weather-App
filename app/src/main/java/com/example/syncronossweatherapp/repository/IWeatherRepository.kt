package com.example.syncronossweatherapp.repository

interface IWeatherRepository {
    fun getWeatherByLocation(lat: String, lon: String)
}