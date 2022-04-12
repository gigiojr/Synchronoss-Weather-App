package com.example.syncronossweatherapp.repository

interface ILocationRepository {
    fun getLocation(callback: ILocationCallback)
}