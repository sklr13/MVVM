package com.example.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.data.domain_entity.RepositoryModel
import com.example.core.data.mappers.RepoNetworkDomainMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
    private val mapper: RepoNetworkDomainMapper
) : ViewModel() {
    private val repositories: MutableLiveData<List<RepositoryModel>> by lazy {
        MutableLiveData<List<RepositoryModel>>().also {
            loadRepos()
        }
    }

    fun getRepos(): LiveData<List<RepositoryModel>> {
        return repositories
    }

    private fun loadRepos() {
        repository.getReposList()
            .subscribeOn(Schedulers.io())
            .map(mapper)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ repositories.postValue(it) }, {})
    }
}

