package com.example.home

import com.example.network.RetrofitService
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class HomeRepositoryTest {

    private var retrofitService: RetrofitService = mockk(relaxed = true)
    private lateinit var homeRepository: HomeRepository

    @Before
    fun setUp() {
        homeRepository = HomeRepository(retrofitService)
    }

    @Test
    fun getReposList() {
        homeRepository.getReposList()
        verify { retrofitService.getReposList() }
    }
}