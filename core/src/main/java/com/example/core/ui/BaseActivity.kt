package com.example.core.ui

import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.core.utils.NetworkErrorHelper

abstract class BaseActivity : AppCompatActivity() {

    private var runRetry: (() -> Unit)? = null

    fun handleError(exc: Throwable?, r: (() -> Unit)?) {
        NetworkErrorHelper.handle(exc, object : NetworkErrorHelper.Callback {
            override fun onNoInternet() {
                saveRetryAction(r, exc?.message)
            }

            override fun onAPI(isUnAuthorize: Boolean, exc: Throwable, statusCode: Int) {
                if (isUnAuthorize) {
                    handleUnAuthorized(statusCode)
                } else {
                    onUnknownException(exc)
                }
            }

            override fun onUnknownException(throwable: Throwable?) {
                saveRetryAction(r, exc?.message)
            }
        })
    }

    @CallSuper
    open fun saveRetryAction(r: (() -> Unit)?, message: String?) {
        runRetry = r
    }

    fun getRetryAction(): (() -> Unit)? = runRetry

    private fun handleUnAuthorized(statusCode: Int) {
        //TODO implement handling of auth errors
    }

    fun FragmentManager.getCurrentNavigationFragment(): Fragment? =
        primaryNavigationFragment?.childFragmentManager?.fragments?.first()

    abstract fun showProgress()

    abstract fun hideProgress()
}
