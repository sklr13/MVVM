package com.example.core

import android.content.Context
import android.content.res.Resources
import com.example.core.activity_router.BaseRouter

interface AppComponentContract {

    fun provideContext(): Context

    fun provideResources(): Resources

    fun provideActivityRouter(): BaseRouter
}
