package com.example.syncronossweatherapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.*
import com.example.syncronossweatherapp.model.WeatherResponse
import com.example.syncronossweatherapp.repository.IDataStorageRepository
import com.example.syncronossweatherapp.worker.WeatherWork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val dataStorageRepository: IDataStorageRepository
) : ViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading : LiveData<Boolean> = _isLoading

    private val _weather = MutableLiveData<WeatherResponse?>(null)
    val weather : LiveData<WeatherResponse?> = _weather

    fun startWorker(workManager: WorkManager): UUID {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .build()

        val weatherWork = PeriodicWorkRequestBuilder<WeatherWork>(2, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()

        workManager.enqueueUniquePeriodicWork(
            WeatherWork.WORK_NAME,
            ExistingPeriodicWorkPolicy.REPLACE,
            weatherWork)

        return weatherWork.id
    }

    fun setWorkStatus(state: WorkInfo.State) {
        when(state) {
            WorkInfo.State.RUNNING -> _isLoading.postValue(true)
            WorkInfo.State.ENQUEUED -> updateData()
            else -> _isLoading.postValue(false)
        }
    }

    fun updateData() {
        _isLoading.postValue(true)

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val w = dataStorageRepository.getLastWeather()
                _weather.postValue(w)
                _isLoading.postValue(false)
            }
        }
    }
}