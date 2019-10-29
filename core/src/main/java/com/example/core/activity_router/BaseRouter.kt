package com.example.core.activity_router

import android.app.Activity

interface BaseRouter {

    companion object {
        const val SPLASH_ACTIVITY = "splash"
        const val HOME_ACTIVITY = "home"
    }

    fun startActivity(currentActivityIntent: Activity, activityKey: String)
}