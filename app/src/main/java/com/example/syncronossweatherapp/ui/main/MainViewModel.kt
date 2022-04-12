package com.example.syncronossweatherapp.ui.main

import androidx.lifecycle.ViewModel
import com.example.syncronossweatherapp.repository.IDataStorageRepository
import com.example.syncronossweatherapp.repository.IWeatherRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val dataStorageRepository: IDataStorageRepository,
    private val weatherRepository: IWeatherRepository
) : ViewModel() {

}