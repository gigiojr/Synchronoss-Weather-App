package com.example.syncronossweatherapp.repository

import android.location.Location

interface ILocationRepository {
    suspend fun getLocation(): Location?
}