package com.example.syncronossweatherapp.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module(includes = [MainModule::class, NetworkModule::class, RepositoryModule::class, WorkerModule::class])
class ApplicationModule {

    @Provides
    fun provideApplication(app: Application): Context = app.applicationContext
}