package com.example.home.di

import com.example.core.di.AppComponentContract
import com.example.home.HomeFragment

import dagger.Component

@Component(dependencies = [AppComponentContract::class])
internal interface HomeComponent {

    fun inject(target: HomeFragment)

    companion object {
        fun setup(appComponentContract: AppComponentContract): HomeComponent {
            return DaggerHomeComponent
                .builder()
                .plus(appComponentContract)
                .build()
        }
    }

    @Component.Builder
    interface Builder {

        fun plus(appComponentContract: AppComponentContract):Builder

        fun build(): HomeComponent
    }
}
