package com.example.core.data.mappers

import com.example.core.data.network_entity.RepositoryNetworkModel
import com.example.core.data.network_entity.responce.RepositoryResponce
import io.mockk.mockk
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class RepoNetworkDomainMapperTest {

    private lateinit var mapper: RepoNetworkDomainMapper

    @Before
    fun setUp() {
        mapper = RepoNetworkDomainMapper()
    }

    @Test
    fun applyTest() {
        val expectedId1 = 12345
        val expectedName1 = "name"
        val expectedDescription1 = "repos description"

        val expectedId2 = 12345
        val expectedName2 = "name"
        val expectedDescription2 = "repos description"

        val networkModel1 = RepositoryNetworkModel(expectedId1, expectedName1, expectedDescription1)
        val networkModel2 = RepositoryNetworkModel(expectedId2, expectedName2, expectedDescription2)

        val responce = RepositoryResponce(mockk())
        responce.reposList = arrayListOf(networkModel1, networkModel2)

        val result = mapper.apply(responce)

        assertEquals(expectedId1, result[0].id)
        assertEquals(expectedName1, result[0].name)
        assertEquals(expectedDescription1, result[0].description)

        assertEquals(expectedId2, result[1].id)
        assertEquals(expectedName2, result[1].name)
        assertEquals(expectedDescription2, result[1].description)
    }
}