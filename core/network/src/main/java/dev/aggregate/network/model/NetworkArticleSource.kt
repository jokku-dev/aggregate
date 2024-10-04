package dev.aggregate.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkArticleSource(
    /**
     * Source identifier
     */
    @SerialName("id") val id: String?,
    /**
     * Source name
     */
    @SerialName("name") val name: String
)
