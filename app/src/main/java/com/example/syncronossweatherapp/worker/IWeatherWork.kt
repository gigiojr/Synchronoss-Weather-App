package com.example.syncronossweatherapp.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters

interface IWeatherWork<T : ListenableWorker> {
    fun create(appContext: Context, params: WorkerParameters): T
}