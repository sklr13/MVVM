package com.example

import android.app.Activity
import android.content.Intent
import com.example.core.activity_router.BaseRouter
import com.example.core.activity_router.BaseRouter.Companion.HOME_ACTIVITY
import com.example.home.HomeActivity
import java.lang.UnsupportedOperationException

class ActivityRouter : BaseRouter {

    override fun startActivity(currentActivityIntent: Activity, activityKey: String) {
        currentActivityIntent.startActivity(createIntentByKey(currentActivityIntent, activityKey))
    }

    private fun createIntentByKey(activity: Activity, activityKey: String): Intent? =
        Intent(activity, getClassByKey(activityKey))

    private fun getClassByKey(activityKey: String): Class<*>? = when (activityKey) {
        HOME_ACTIVITY -> HomeActivity::class.java
        //todo add own exception
        else -> throw UnsupportedOperationException(activityKey)
    }
}