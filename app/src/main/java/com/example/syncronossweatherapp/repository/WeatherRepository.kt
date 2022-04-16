package com.example.syncronossweatherapp.repository

import android.util.Log
import com.example.syncronossweatherapp.model.WeatherResponse
import com.example.syncronossweatherapp.network.OpenWeatherMapService
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleOnSubscribe
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherMapService: OpenWeatherMapService
) : IWeatherRepository {

    override suspend fun getWeatherByLocation(lat: String, lon: String): WeatherResponse? {
        return Single.create(SingleOnSubscribe<WeatherResponse> { emitter ->
            weatherMapService.getWeather(lat, lon).enqueue(object : Callback<WeatherResponse>{
                override fun onResponse(call: Call<WeatherResponse>?, response: Response<WeatherResponse>?) {
                    response?.body()?.let {
                        emitter.onSuccess(it)
                    } ?: run {
                        emitter.onError(Throwable("Weather not found"))
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>?, t: Throwable?) {
                    Log.e("WeatherRepository", t.toString())
                    emitter.onError(t)
                }
            })
        }).blockingGet()
    }
}