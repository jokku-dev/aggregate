package dev.jokku.aggregate.data.remote.model

import dev.jokku.aggregate.data.local.database.entity.ArticleEntitySource
import dev.jokku.aggregate.data.mapper.FromRemoteMapper
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkArticleSource(
    /**
     * Source identifier
     */
    @SerialName("id")
    val id: String,
    /**
     * Source name
     */
    @SerialName("name")
    val name: String
) : FromRemoteMapper<ArticleEntitySource> {
    override fun asEntity() = ArticleEntitySource(
        id = id,
        name = name
    )
}