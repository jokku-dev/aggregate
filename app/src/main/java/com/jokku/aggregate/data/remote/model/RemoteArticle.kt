package com.jokku.aggregate.data.remote.model

import com.jokku.aggregate.data.local.database.entity.LocalTopHeadlinesArticle
import com.jokku.aggregate.data.mapper.DataModelMapper
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteArticle(
    /**
     * The author of the article
     */
    @SerialName("author")
    val author: String,
    /**
     * The unformatted content of the article, where available. This is truncated to 200 chars.
     */
    @SerialName("content")
    val content: String,
    /**
     * A description or snippet from the article.
     */
    @SerialName("description")
    val description: String,
    /**
     * The date and time that the article was published, in UTC (+000)
     */
    @SerialName("publishedAt")
    val publishedAt: String,
    /**
     * The source this article came from.
     */
    @SerialName("source")
    val source: RemoteArticleSource,
    /**
     * The headline or title of the article.
     */
    @SerialName("title")
    val title: String,
    /**
     * The direct URL to the article.
     */
    @SerialName("url")
    val url: String,
    /**
     * The URL to a relevant image for the article.
     */
    @SerialName("urlToImage")
    val urlToImage: String
) : DataModelMapper<LocalTopHeadlinesArticle> {

    override fun map() = LocalTopHeadlinesArticle(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = source.map(),
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}