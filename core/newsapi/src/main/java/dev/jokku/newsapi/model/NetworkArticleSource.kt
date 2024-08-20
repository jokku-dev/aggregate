package dev.jokku.newsapi.model

import dev.jokku.aggregate.data.local.database.entity.ArticleEntitySource
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
) : dev.jokku.aggregate.data.mapper.FromRemoteMapper<dev.jokku.aggregate.data.local.database.entity.ArticleEntitySource> {
    override fun asEntity() = dev.jokku.aggregate.data.local.database.entity.ArticleEntitySource(
        id = id,
        name = name
    )
}