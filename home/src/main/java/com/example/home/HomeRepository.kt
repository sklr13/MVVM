package com.example.home

import com.example.core.data.mappers.RepoNetworkDomainMapper
import com.example.core.data.network_entity.responce.RepositoryResponce
import com.example.network.retrofit.RetrofitService
import io.reactivex.Single
import javax.inject.Inject

class HomeRepository @Inject constructor(private val retrofitService: RetrofitService) {

    fun getReposList(): Single<RepositoryResponce> =
        retrofitService.getReposList()

}