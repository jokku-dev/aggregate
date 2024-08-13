package dev.jokku.newsapi.model

import dev.jokku.newsapi.util.DateTimeUtcSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class ArticleNetwork(
    @SerialName("source") val source: SourceNetwork,
    @SerialName("author") val author: String,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("url") val url: String,
    @SerialName("urlToImage") val urlToImage: String,
    @SerialName("publishedAt") @Serializable(with = DateTimeUtcSerializer::class)
    val publishedAt: Date,
    @SerialName("content") val content: String,
)