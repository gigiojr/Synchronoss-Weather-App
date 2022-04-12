package com.example.syncronossweatherapp.model

import android.text.format.DateFormat
import org.json.JSONObject
import java.util.*

class WeatherModel {

    var date: Date? = null

    var weatherMain: String? = null
    var weatherIcon: String? = null
    var weatherDescription: String? = null

    var mainTemp = 0
    var mainTempMin = 0
    var mainTempMax = 0
    var mainPressure = 0
    var mainHumidity = 0

    var windSpeed = 0

    var cloudsAll = 0

    var cityName: String? = null
    var latitude: Double? = null
    var longitude: Double? = null

    fun WeatherModel(json: JSONObject) {
        date = Date(json.getLong(LABEL_DATA) * 1000)

        val weatherArray = json.getJSONArray(LABEL_WEATHER)
        if (weatherArray.length() > 0) {
            val weather = weatherArray.getJSONObject(0)
            weatherMain = weather.getString(LABEL_WEATHER_MAIN)
            weatherIcon = weather.getString(LABEL_WEATHER_ICON)
            weatherDescription = weather.getString(LABEL_WEATHER_DESCRIPTION)
        }

        val main = json.getJSONObject(LABEL_MAIN)
        if (main.has(LABEL_MAIN_TEMP)) {
            mainTemp = main.getInt(LABEL_MAIN_TEMP)
            mainTempMin = main.getInt(LABEL_MAIN_TEMP_MIN)
            mainTempMax = main.getInt(LABEL_MAIN_TEMP_MAX)
            mainPressure = main.getInt(LABEL_MAIN_PRESSURE)
            mainHumidity = main.getInt(LABEL_MAIN_HUMIDITY)
        }

        val wind = json.getJSONObject(LABEL_WIND)
        if (wind.has(LABEL_WIND_SPEED)) {
            windSpeed = wind.getInt(LABEL_WIND_SPEED)
        }

        val clouds = json.getJSONObject(LABEL_CLOUDS)
        if (clouds.has(LABEL_CLOUDS_ALL)) {
            cloudsAll = clouds.getInt(LABEL_CLOUDS_ALL)
        }

        if (json.has(LABEL_COORD)) {
            val coord = json.getJSONObject(LABEL_COORD)
            if (coord.has(LABEL_COORD_LAT) && coord.has(LABEL_COORD_LNG)) {
                latitude = coord.getDouble(LABEL_COORD_LAT)
                longitude = coord.getDouble(LABEL_COORD_LNG)
            }
        }

        if (json.has(LABEL_SYS)) {
            val sys = json.getJSONObject(LABEL_SYS)
            if (sys.has(LABEL_SYS_NAME)) {
                cityName = sys.getString(LABEL_SYS_NAME)
            }
        }
    }

    fun getIconUrl(): String? {
        return if (weatherIcon != null && !weatherIcon!!.isEmpty()) {
            "https://openweathermap.org/img/w/$weatherIcon"
        } else {
            weatherIcon
        }
    }

    fun getDateFormatted(): String {
        return if (date != null) {
            DateFormat.format("dd/MM/yyyy - hh:mm", date) as String
        } else ""
    }

    companion object {
        // Date of forecast
        private const val LABEL_DATA = "dt"

        // Weather object
        private const val LABEL_WEATHER = "weather"

        // Group of weather parameters
        private const val LABEL_WEATHER_MAIN = "main"

        // Weather icon id
        private const val LABEL_WEATHER_ICON = "icon"

        // Weather condition within the group
        private const val LABEL_WEATHER_DESCRIPTION = "description"

        // main object
        private const val LABEL_MAIN = "main"

        // Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
        private const val LABEL_MAIN_TEMP = "temp"

        // Atmospheric pressure (on the sea level, if there is no sea_level or grnd_level data), hPa
        private const val LABEL_MAIN_PRESSURE = "pressure"

        // Humidity, %
        private const val LABEL_MAIN_HUMIDITY = "humidity"

        // Minimum temperature at the moment. This is deviation from current temp that is possible
        // for large cities and megalopolises geographically expanded (use these parameter
        // optionally). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
        private const val LABEL_MAIN_TEMP_MIN = "temp_min"

        // Maximum temperature at the moment. This is deviation from current temp that is possible
        // for large cities and megalopolises geographically expanded (use these parameter
        // optionally). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
        private const val LABEL_MAIN_TEMP_MAX = "temp_max"

        // wind object
        private const val LABEL_WIND = "wind"

        // Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour.
        private const val LABEL_WIND_SPEED = "speed"

        // Cloudiness, %
        private const val LABEL_CLOUDS = "clouds"

        private const val LABEL_CLOUDS_ALL = "all"

        // Coord object
        private const val LABEL_COORD = "coord"

        // Latitude
        private const val LABEL_COORD_LAT = "lat"

        // Longitude
        private const val LABEL_COORD_LNG = "lon"

        // Sys object
        private const val LABEL_SYS = "sys"

        // City name
        private const val LABEL_SYS_NAME = "name"
    }
}