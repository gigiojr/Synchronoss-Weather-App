package com.example.syncronossweatherapp.model

data class WeatherDataResponse(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)
