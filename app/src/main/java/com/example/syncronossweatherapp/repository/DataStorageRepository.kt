package com.example.syncronossweatherapp.repository

import com.example.syncronossweatherapp.dataSource.IUserPreference
import com.example.syncronossweatherapp.model.WeatherResponse
import javax.inject.Inject

class DataStorageRepository @Inject constructor(
    private val userPreferences: IUserPreference
) : IDataStorageRepository {

    override suspend fun updateWeather(lastWeather: WeatherResponse) {
        userPreferences.setPreference(IUserPreference.TAG_WEATHER, lastWeather)
    }

    override suspend fun getLastWeather(): WeatherResponse? {
        return userPreferences.getWeather(IUserPreference.TAG_WEATHER)
    }
}