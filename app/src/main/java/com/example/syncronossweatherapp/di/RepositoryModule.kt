package com.example.syncronossweatherapp.di

import android.content.Context
import com.example.syncronossweatherapp.dataSource.IUserPreference
import com.example.syncronossweatherapp.dataSource.UserPreference
import com.example.syncronossweatherapp.network.OpenWeatherMapService
import com.example.syncronossweatherapp.repository.*
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideUserPreference(context: Context): IUserPreference {
        return UserPreference(context)
    }

    @Provides
    fun provideDataStorageRepository(userPreference: UserPreference): IDataStorageRepository {
        return DataStorageRepository(userPreference)
    }

    @Provides
    fun provideLocationRepository(context: Context): ILocationRepository {
        return LocationRepository(context)
    }

    @Provides
    fun provideWeatherRepository(weatherMapService: OpenWeatherMapService): IWeatherRepository {
        return WeatherRepository(weatherMapService)
    }
}