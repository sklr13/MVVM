package com.example.splash.di

import com.example.core.AppComponentContract
import com.example.splash.SplashActivity

import dagger.Component

@PresenterScope
@Component(dependencies = [AppComponentContract::class])
internal interface SplashComponent {

    fun inject(activity: SplashActivity)

    companion object {
        fun setup(appComponentContract: AppComponentContract): SplashComponent {
            return DaggerSplashComponent
                .builder()
                .plus(appComponentContract)
                .build()
        }
    }

    @Component.Builder
    interface Builder {

        fun plus(appComponentContract: AppComponentContract):Builder

        fun build(): SplashComponent
    }
}
