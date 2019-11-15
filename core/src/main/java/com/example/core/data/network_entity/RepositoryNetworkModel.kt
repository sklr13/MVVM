package com.example.core.data.network_entity

import com.google.gson.annotations.SerializedName

data class RepositoryNetworkModel(
    @field:SerializedName("id") val id: Int? = null,
    @field:SerializedName("name") val name: String? = null,
    @field:SerializedName("description") val description: String? = null
)