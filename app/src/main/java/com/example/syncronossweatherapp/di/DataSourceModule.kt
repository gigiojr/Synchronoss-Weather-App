package com.example.syncronossweatherapp.di

import com.example.syncronossweatherapp.repository.ILocationRepository
import com.example.syncronossweatherapp.repository.IWeatherRepository
import com.example.syncronossweatherapp.repository.LocationRepository
import com.example.syncronossweatherapp.repository.WeatherRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataSourceModule {

    @Binds
    abstract fun provideWeatherRepository(repository: WeatherRepository): IWeatherRepository

    @Binds
    abstract fun provideLocationRepository(repository: LocationRepository): ILocationRepository
}