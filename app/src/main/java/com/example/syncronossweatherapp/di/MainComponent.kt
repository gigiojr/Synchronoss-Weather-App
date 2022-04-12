package com.example.syncronossweatherapp.di

import com.example.syncronossweatherapp.ui.main.MainActivity
import com.example.syncronossweatherapp.ui.main.MainFragment
import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class, DataSourceModule::class, NetworkModule::class])
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(activity: MainActivity)
    fun inject(activity: MainFragment)
}