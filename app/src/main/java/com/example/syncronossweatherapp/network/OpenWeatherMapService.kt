package com.example.syncronossweatherapp.network

import com.example.syncronossweatherapp.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapService {
    @GET("weather")
    fun getWeather(
        @Query("lat") lat: String?,
        @Query("lon") lon: String?,
        @Query("units") units: String = "metric",
        @Query("appid") appid: String = "5ad7218f2e11df834b0eaf3a33a39d2a"
    ): Call<WeatherResponse>
}