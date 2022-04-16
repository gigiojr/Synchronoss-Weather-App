package com.example.syncronossweatherapp.dataSource

import com.example.syncronossweatherapp.model.WeatherResponse

interface IUserPreference {
    companion object {
        const val PREFS_NAME = "preferences"

        const val TAG_WEATHER = "weather"
    }

    fun getPreference(tag: String?): String?
    fun setPreference(tag: String?, value: String?)

    fun getWeather(tag: String): WeatherResponse?
    fun setPreference(tag: String, weatherResponse: WeatherResponse)
}