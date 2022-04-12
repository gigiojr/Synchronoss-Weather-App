package com.example.syncronossweatherapp.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.Module

@Component(modules = [DataSourceModule::class, NetworkModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }

    fun mainComponent(): MainComponent.Factory
}

@Module(subcomponents = [MainComponent::class])
object SubcomponentsModule