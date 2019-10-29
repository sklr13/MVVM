package com.example.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.example.ActivityRouter
import com.example.core.activity_router.BaseRouter

import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    fun provideResources(application: Application): Resources {
        return application.resources
    }

    @Provides
    fun provideActivityRouter(): BaseRouter {
        return ActivityRouter()
    }

}
