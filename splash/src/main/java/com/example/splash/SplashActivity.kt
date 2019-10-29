package com.example.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.core.BaseApplication
import com.example.core.activity_router.BaseRouter
import com.example.splash.di.SplashComponent
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var activityRouter:BaseRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        SplashComponent.setup((application as BaseApplication).getComponent()).inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_layout)
        Handler().postDelayed({
            activityRouter.startActivity(this, BaseRouter.HOME_ACTIVITY)
        },3000)
    }
}