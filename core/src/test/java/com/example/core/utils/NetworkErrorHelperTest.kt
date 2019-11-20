package com.example.core.utils

import com.example.core.network.error.ApiException
import com.example.core.network.error.AuthorizationException
import com.example.core.network.error.ErrorInfo
import io.mockk.mockk
import io.mockk.verify
import org.json.JSONException
import org.junit.Before
import org.junit.Test
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class NetworkErrorHelperTest {

    @Before
    @Throws(Exception::class)
    fun setUp() {

    }

    @Test
    fun handle_socketTimeoutException() {
        val callback: NetworkErrorHelper.Callback = mockk(relaxed = true)

        NetworkErrorHelper.handle(SocketTimeoutException(), callback)

        verify { callback.onNoInternet() }
    }

    @Test
    fun handle_unknownHostException() {
        val callback: NetworkErrorHelper.Callback = mockk(relaxed = true)

        NetworkErrorHelper.handle(UnknownHostException(), callback)

        verify { callback.onNoInternet() }
    }

    @Test
    fun handle_JSONException() {
        val callback: NetworkErrorHelper.Callback = mockk(relaxed = true)

        NetworkErrorHelper.handle(JSONException("Test"), callback)

        verify { callback.onNoInternet() }
    }

    @Test
    fun handle_authorizationException() {
        val callback: NetworkErrorHelper.Callback = mockk(relaxed = true)
        val exception = AuthorizationException(401, "TestMessage")
        NetworkErrorHelper.handle(exception, callback)

        verify { callback.onAPI(true, exception, 401) }
    }

    @Test
    fun handle_apiException() {
        val callback: NetworkErrorHelper.Callback = mockk(relaxed = true)
        val errorInfo = ErrorInfo()
        errorInfo.errorCode = 500.toString()
        errorInfo.errorMessage = "Test error message"
        val exception = ApiException(errorInfo)
        NetworkErrorHelper.handle(exception, callback)

        verify { callback.onAPI(false, exception, 500) }
    }

    @Test
    fun handle_unknownException() {
        val callback: NetworkErrorHelper.Callback = mockk(relaxed = true)
        NetworkErrorHelper.handle(NullPointerException(), callback)

        verify { callback.onUnknownException(any()) }
    }
}
