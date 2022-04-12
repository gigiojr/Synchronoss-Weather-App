package com.example.syncronossweatherapp.repository

import android.util.Log
import com.example.syncronossweatherapp.model.WeatherResponse
import com.example.syncronossweatherapp.network.OpenWeatherMapService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val dataStorageRepository: IDataStorageRepository,
    private val weatherMapService: OpenWeatherMapService
) : IWeatherRepository {

    override fun getWeatherByLocation(lat: String, lon: String) {
        weatherMapService.getWeather(lat, lon).enqueue(object : Callback<WeatherResponse>{
            override fun onResponse(call: Call<WeatherResponse>?, response: Response<WeatherResponse>?) {
                response?.body()?.let {
                    dataStorageRepository.updateWeather(it);
                }
            }

            override fun onFailure(call: Call<WeatherResponse>?, t: Throwable?) {
                Log.e("WeatherRepository", t.toString())
            }
        })
    }
}