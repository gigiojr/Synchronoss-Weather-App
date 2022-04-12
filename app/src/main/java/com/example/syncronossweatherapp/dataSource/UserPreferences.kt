package com.example.syncronossweatherapp.dataSource

import android.content.Context

class UserPreferences {

    companion object {
        private const val PREFS_NAME = "preferences"
    }

    fun setPreference(context: Context, item: String?, value: String?): String? {
        val settings = context.getSharedPreferences(PREFS_NAME, 0)
        val editor = settings.edit()
        editor.putString(item, value)
        editor.apply()
        return value
    }

    fun getPreference(context: Context, item: String?): String? {
        val preferences = context.getSharedPreferences(PREFS_NAME, 0)
        return preferences.getString(item, "")
    }
}