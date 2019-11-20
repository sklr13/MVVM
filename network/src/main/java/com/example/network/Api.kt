package com.example.network

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("orgs/square/repos")
    fun getReposList(): Single<Response<ResponseBody>>
}