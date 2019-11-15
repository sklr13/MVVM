package com.example

import android.app.Activity
import android.content.Intent
import com.example.core.activity_router.BaseRouter
import com.example.core.activity_router.BaseRouter.Companion.HOME_ACTIVITY
import java.lang.UnsupportedOperationException

class ActivityRouter : BaseRouter {

    override fun startActivity(currentActivityIntent: Activity, activityKey: String) {
        currentActivityIntent.startActivity(
            createIntentByKey(
                currentActivityIntent,
                activityKey,
                false
            )
        )
    }

    override fun replaceActivity(currentActivityIntent: Activity, activityKey: String) {
        currentActivityIntent.startActivity(
            createIntentByKey(
                currentActivityIntent,
                activityKey,
                true
            )
        )
    }

    private fun createIntentByKey(
        activity: Activity,
        activityKey: String,
        isClearBackStack: Boolean
    ): Intent? =
        Intent(activity, getClassByKey(activityKey)).apply {
            if (isClearBackStack) {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }
        }

    private fun getClassByKey(activityKey: String): Class<*>? = when (activityKey) {
        HOME_ACTIVITY -> MainActivity::class.java
        //todo add own exception
        else -> throw UnsupportedOperationException(activityKey)
    }
}