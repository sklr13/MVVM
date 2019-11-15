package com.example.network.retrofit

import com.example.core.data.network_entity.responce.RepositoryResponce
import com.example.core.network.RetrofitResponseTransformer
import com.google.gson.Gson
import io.reactivex.Single
import javax.inject.Inject

class RetrofitService @Inject constructor(private val api: Api, private val gson: Gson) {

    fun getReposList(): Single<RepositoryResponce> {
        return api.getReposList()
            .lift(RetrofitResponseTransformer(RepositoryResponce(gson)))
    }
}
