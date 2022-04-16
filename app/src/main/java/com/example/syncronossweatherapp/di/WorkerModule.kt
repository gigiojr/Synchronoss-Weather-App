package com.example.syncronossweatherapp.di

import androidx.work.ListenableWorker
import com.example.syncronossweatherapp.worker.IWeatherWork
import com.example.syncronossweatherapp.worker.WeatherWork
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@MapKey
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class WorkerKey(val value: KClass<out ListenableWorker>)

@Module
interface WorkerModule {
    @Binds
    @IntoMap
    @WorkerKey(WeatherWork::class)
    fun bindWeatherWorker(worker: WeatherWork.Factory): IWeatherWork<out ListenableWorker>
}