package com.example.syncronossweatherapp.model

data class WeatherResponse(
    var weather: List<WeatherDataResponse>,
    var main: MainWeatherResponse,
    var wind: WindDataResponse,
    var clouds: CloudsDataResponse,
    var name: String,
    var id: Int
)
