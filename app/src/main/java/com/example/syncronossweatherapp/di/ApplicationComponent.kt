package com.example.syncronossweatherapp.di

import android.app.Application
import com.example.syncronossweatherapp.WeatherApplication
import com.example.syncronossweatherapp.ui.main.MainActivity
import com.example.syncronossweatherapp.ui.main.MainFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }

    fun inject(weatherApplication: WeatherApplication)
    fun inject(mainActivity: MainActivity)
    fun inject(mainFragment: MainFragment)
}