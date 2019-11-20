package com.example.home

import com.example.core.data.domain_entity.RepositoryModel
import com.example.core.data.mappers.RepoNetworkDomainMapper
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.core.data.network_entity.responce.RepositoryResponce
import io.mockk.every
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule

class HomeViewModelTest {

    @JvmField
    @Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @JvmField
    @Rule
    var trampolineSchedulerRule = TrampolineSchedulerRule()

    private val mapper: RepoNetworkDomainMapper = mockk()
    private val repository: HomeRepository = mockk()
    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        homeViewModel = HomeViewModel(repository, mapper)
    }

    @Test
    fun getReposTest() {
        val mockResponse: RepositoryResponce = mockk()
        every { repository.getReposList() } returns Single.just(mockResponse)
        homeViewModel.getRepos()
        verify { repository.getReposList() }
    }

    @Test
    fun getReposResultTest() {
        val mockResponse: RepositoryResponce = mockk()
        val mockItem = mockk<RepositoryModel>()
        val mockList: List<RepositoryModel> = arrayListOf(mockItem)
        every { repository.getReposList() } returns Single.just(mockResponse)
        every { mapper.apply(any()) } returns mockList
        val liveData = homeViewModel.getRepos()
        assertTrue(liveData.value?.isNotEmpty() ?: false)
    }

    @Test
    fun getReposErrorTest() {
        val mockError = Throwable()
        every { repository.getReposList() } returns Single.error(mockError)
        homeViewModel.getRepos()
        val liveData = homeViewModel.getErrors()
        assertEquals(mockError, liveData.value?.throwable)
    }

}