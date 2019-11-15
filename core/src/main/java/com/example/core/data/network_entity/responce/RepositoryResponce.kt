package com.example.core.data.network_entity.responce

import com.example.core.data.network_entity.RepositoryNetworkModel
import com.example.core.network.AbstractResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RepositoryResponce(private val gson: Gson) : AbstractResponse() {
    var reposList: List<RepositoryNetworkModel>? = arrayListOf()

    override fun parseSuccess(body: String?) {
        reposList = gson.fromJson(body, object : TypeToken<ArrayList<RepositoryNetworkModel>>() {}.type)
    }
}