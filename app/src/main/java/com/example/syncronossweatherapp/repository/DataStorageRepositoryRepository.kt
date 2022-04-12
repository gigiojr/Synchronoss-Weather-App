package com.example.syncronossweatherapp.repository

import com.example.syncronossweatherapp.dataSource.IUserPreference
import com.example.syncronossweatherapp.model.WeatherResponse
import javax.inject.Inject

class DataStorageRepositoryRepository @Inject constructor(
    private val userPreferences: IUserPreference
) : IDataStorageRepository {

    override fun updateWeather(lastWeather: WeatherResponse) {
        userPreferences.setPreference(IUserPreference.TAG_WEATHER, lastWeather)
    }

    override fun getLastWeather(): WeatherResponse? {
        return userPreferences.getPreference(IUserPreference.TAG_WEATHER)
    }
}