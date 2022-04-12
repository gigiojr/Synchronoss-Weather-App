package com.example.syncronossweatherapp.repository

import android.location.Location

interface ILocationCallback {
    fun onLocation(location : Location)
    fun onFailure()
}