package com.example.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.data.ErrorModel
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
    private val errorData: MutableLiveData<ErrorModel> = MutableLiveData()

    fun getRepos(): LiveData<List<RepositoryModel>> {
        return repositories
    }

    fun getErrors(): LiveData<ErrorModel> {
        return errorData
    }

    private fun loadRepos() {
        repository.getReposList()
            .subscribeOn(Schedulers.io())
            .map(mapper)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ repositories.value = it },
                { t -> handleError(t, ::loadRepos)})
    }

    private fun handleError(
        throwable: Throwable?,
        runnable: () -> Unit
    ) {
        errorData.value = ErrorModel(throwable, runnable)
    }
}

