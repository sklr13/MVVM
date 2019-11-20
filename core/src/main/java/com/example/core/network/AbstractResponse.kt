package com.example.core.network

import com.example.core.network.error.AuthorizationException
import com.example.core.network.error.ErrorInfo
import org.json.JSONException

abstract class AbstractResponse protected constructor() {

    var errorInfo: ErrorInfo
        protected set

    val isSuccess: Boolean
        get() = !errorInfo.hasError()

    init {
        errorInfo = ErrorInfo()
    }

    fun parseError(body: String?, errorCode: Int) {
        errorInfo.errorCode = errorCode.toString()
        if (body != null)
            try {
                errorInfo.parse(body)
                if (!errorInfo.hasError()) {
                    errorInfo.errorCode = errorCode.toString()
                }

                if (errorCode == HttpStatus.SC_UNAUTHORIZED ||
                    errorCode == HttpStatus.SC_BAD_REQUEST && errorInfo.errorCode.equals("40030") ||
                    errorCode == HttpStatus.SC_FORBIDDEN && errorInfo.errorCode.equals("invalid_token")
                ) {
                    throw AuthorizationException(errorCode, body)
                }
            } catch (e: JSONException) {
                errorInfo.errorMessage = body
                errorInfo.errorCode = errorCode.toString()
            }

    }

    @Throws(Exception::class)
    abstract fun parseSuccess(body: String?)
}
