package com.example.core.data.mappers

import com.example.core.data.domain_entity.RepositoryModel
import com.example.core.data.network_entity.responce.RepositoryResponce
import io.reactivex.functions.Function;
import javax.inject.Inject

class RepoNetworkDomainMapper @Inject constructor() :
    Function<RepositoryResponce, List<RepositoryModel>> {
    override fun apply(input: RepositoryResponce): List<RepositoryModel> {
        var list = ArrayList<RepositoryModel>()
        var inputCOllection = input.reposList

        inputCOllection?.let {
            for (repoItem in it) {
                list.add(RepositoryModel(repoItem.id, repoItem.name, repoItem.description))
            }
        }
        return list
    }
}
