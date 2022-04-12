package com.example.syncronossweatherapp.di

import com.example.syncronossweatherapp.dataSource.IUserPreference
import com.example.syncronossweatherapp.dataSource.UserPreference
import com.example.syncronossweatherapp.repository.*
import dagger.Binds
import dagger.Module

@Module
abstract class DataSourceModule {

    @Binds
    abstract fun provideUserPreference(userPreference: UserPreference): IUserPreference

    @Binds
    abstract fun provideDataStorageRepository(repository: DataStorageRepositoryRepository): IDataStorageRepository

    @Binds
    abstract fun provideLocationRepository(repository: LocationRepository): ILocationRepository

    @Binds
    abstract fun provideWeatherRepository(repository: WeatherRepository): IWeatherRepository
}