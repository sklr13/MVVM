package com.example.core.di

import android.content.Context
import android.content.res.Resources
import androidx.lifecycle.ViewModelProvider
import com.example.core.activity_router.BaseRouter

interface AppComponentContract {

    fun provideContext(): Context

    fun provideResources(): Resources

    fun provideActivityRouter(): BaseRouter

    fun provideViewModelFactory():  ViewModelProvider.Factory

}
