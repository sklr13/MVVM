package com.example.di

import android.app.Application
import com.example.core.di.AppComponentContract
import com.example.MainActivity
import com.example.core.di.ViewModelFactoryModule
import com.example.home.di.HomeViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [AppModule::class, NetworkModule::class,
        ViewModelFactoryModule::class, HomeViewModelModule::class]
)
interface AppComponent : AppComponentContract {

    fun inject(target: MainActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}