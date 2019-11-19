package com.example.core.utils

import com.example.core.network.error.ApiException
import com.example.core.network.error.AuthorizationException

import org.json.JSONException

import java.net.SocketTimeoutException
import java.net.UnknownHostException

import javax.inject.Inject

import dagger.Reusable

@Reusable
class NetworkErrorHelper @Inject
constructor() {

    interface Callback {

        fun onNoInternet()

        fun onAPI(isUnAuthorize: Boolean, exc: Throwable, statusCode: Int)

        fun onUnknownException(throwable: Throwable?)
    }

    companion object {

        fun handle(throwable: Throwable?, callback: Callback) {
            if (throwable is SocketTimeoutException ||
                throwable is UnknownHostException ||
                throwable is JSONException
            ) {
                callback.onNoInternet()
            } else if (throwable is AuthorizationException) {
                callback.onAPI(true, throwable, throwable.statusCode)
            } else if (throwable is ApiException) {
                callback.onAPI(
                    false,
                    throwable,
                    parseIntOrDefault(throwable.errorInfo.errorCode, 500)
                )
            } else {
                callback.onUnknownException(throwable)
            }
        }

        private fun parseIntOrDefault(text: String?, defaultValue: Int): Int {
            try {
                return Integer.parseInt(text!!)
            } catch (e: NumberFormatException) {
                return defaultValue
            }

        }
    }
}
