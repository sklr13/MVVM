package com.example.di

import android.app.Application
import com.example.core.AppComponentContract
import com.example.MainActivity
import com.example.splash.SplashActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent : AppComponentContract {

    fun inject(target: MainActivity)

    fun inject(target: SplashActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}