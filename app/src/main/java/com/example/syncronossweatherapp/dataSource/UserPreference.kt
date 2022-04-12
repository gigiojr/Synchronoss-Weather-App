package com.example.syncronossweatherapp.dataSource

import android.content.Context
import com.example.syncronossweatherapp.model.WeatherResponse
import com.google.gson.Gson
import javax.inject.Inject


class UserPreference @Inject constructor(private val applicationContext: Context) : IUserPreference {

    override fun getPreference(tag: String?): String? {
        val preferences = applicationContext.getSharedPreferences(IUserPreference.PREFS_NAME, 0)
        return preferences.getString(tag, "")
    }

    override fun setPreference(tag: String?, value: String?) {
        val settings = applicationContext.getSharedPreferences(IUserPreference.PREFS_NAME, 0)
        val editor = settings.edit()
        editor.putString(tag, value)
        editor.apply()
    }

    override fun getPreference(tag: String): WeatherResponse? {
        val preferences = applicationContext.getSharedPreferences(IUserPreference.PREFS_NAME, 0)
        val json: String? = preferences.getString(tag, "")
        return Gson().fromJson(json, WeatherResponse::class.java)
    }

    override fun setPreference(tag: String, weatherResponse: WeatherResponse) {
        val settings = applicationContext.getSharedPreferences(IUserPreference.PREFS_NAME, 0)
        val editor = settings.edit()
        val json = Gson().toJson(weatherResponse)
        editor.putString(tag, json);
        editor.apply()
    }
}