package com.example.di

import android.app.Application
import com.example.core.AppComponentContract
import com.example.home.HomeActivity
import com.example.splash.SplashActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent : AppComponentContract {

    fun inject(target: HomeActivity)

    fun inject(target: SplashActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}