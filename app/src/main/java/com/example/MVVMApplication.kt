package com.example

import android.app.Application
import com.example.core.AppComponentContract
import com.example.core.BaseApplication
import com.example.di.AppComponent
import com.example.di.DaggerAppComponent

class MVVMApplication : Application(),BaseApplication {

    private lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        component = buildComponent()

    }

    private fun buildComponent(): AppComponent {
        return DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }

    override fun getComponent(): AppComponentContract {
        return component
    }
}