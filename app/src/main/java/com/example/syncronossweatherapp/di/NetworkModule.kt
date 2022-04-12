package com.example.syncronossweatherapp.di

import com.example.syncronossweatherapp.network.OpenWeatherMapService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideRetrofitService(): OpenWeatherMapService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://api.openweathermap.org/data/2.5/")
            .build()
            .create(OpenWeatherMapService::class.java)
    }
}