package com.example.core.network

import android.text.TextUtils
import org.json.JSONException
import org.json.JSONObject

class ErrorInfo {
    var errorCode: String? = null
    var errorMessage: String? = null

    val isServerError: Boolean
        get() = !TextUtils.isEmpty(errorCode)

    init {
        errorCode = EMPTY
        errorMessage = EMPTY
    }

    fun hasError(): Boolean {
        return !TextUtils.isEmpty(errorCode) || !TextUtils.isEmpty(errorMessage)
    }

    @Throws(JSONException::class)
    fun parse(response: String) {
        val responseJO = JSONObject(response)
        val faultJO = responseJO.optJSONObject(FAULT)
        if (faultJO != null) {
            val detailJO = faultJO.optJSONObject(DETAIL)
            if (detailJO != null) {
                errorCode = detailJO.optString(ERRORCODE2)
            }
            errorMessage = faultJO.optString(FAULTSTRING)
        }
    }

    companion object {
        private val FAULTSTRING = "faultstring"
        private val ERRORCODE2 = "errorcode"
        private val DETAIL = "detail"
        private val FAULT = "fault"
        private val EMPTY = ""
    }
}