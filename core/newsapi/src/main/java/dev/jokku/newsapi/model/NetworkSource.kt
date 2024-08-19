package dev.jokku.newsapi.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkSource(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
)