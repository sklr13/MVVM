package com.example.core.network

import io.reactivex.SingleObserver
import io.reactivex.SingleOperator
import io.reactivex.disposables.Disposable
import okhttp3.ResponseBody
import retrofit2.Response

class RetrofitResponseTransformer<T : AbstractResponse>(private val response: T) :
    SingleOperator<T, Response<ResponseBody>> {

    @Throws(Exception::class)
    override fun apply(observer: SingleObserver<in T>): SingleObserver<in Response<ResponseBody>> {
        return object : SingleObserver<Response<ResponseBody>> {
            override fun onSubscribe(d: Disposable) {
                observer.onSubscribe(d)
            }

            override fun onSuccess(response: Response<ResponseBody>) {
                try {
                    val responseBody = response.errorBody()
                    if (!response.isSuccessful) {
                        this@RetrofitResponseTransformer.response.parseError(
                            responseBody!!.string(),
                            response.code()
                        )
                    } else {
                        val emptyBody = response.body() == null
                        if (!emptyBody) {
                            this@RetrofitResponseTransformer.response.parseSuccess(response.body()!!.string())
                        }
                    }
                    observer.onSuccess(this@RetrofitResponseTransformer.response)
                } catch (e: Exception) {
                    observer.onError(e)
                }

            }

            override fun onError(e: Throwable) {
                observer.onError(e)
            }
        }
    }
}