package dev.jokku.newsapi.model

import dev.jokku.aggregate.data.local.database.entity.TopHeadlines
import dev.jokku.aggregate.data.local.database.entity.intermediate.TopHeadlinesResponseWithArticles
import dev.jokku.aggregate.data.mapper.mapList
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName
import java.time.LocalDateTime
import java.time.ZoneOffset

@Serializable
data class NetworkTopHeadlines(
    /**
     * If the request was successful or not. Options: ok, error. In the case of error a code and
     * message property will be populated.
     */
    @SerialName("status")
    val status: String,
    /**
     * The results of the request.
     */
    @SerialName("articles")
    val articles: List<NetworkArticle>,
    /**
     * The total number of results available for your request. Only a limited number are shown at a
     * time though, so use the page parameter in your requests to page through them.
     */
    @SerialName("totalResults")
    val totalResults: Int,
    /**
     * Populated in the case of error status
     */
    @SerialName("code")
    val errorCode: String,
    /**
     * Populated in the case of error status
     */
    @SerialName("message")
    val errorMessage: String
) : dev.jokku.aggregate.data.mapper.FromRemoteMapper<TopHeadlinesResponseWithArticles<TopHeadlines>> {
    override fun asEntity() = TopHeadlinesResponseWithArticles<TopHeadlines>(
        response = object : TopHeadlines {
            override val totalResults: Int = this@NetworkTopHeadlines.totalResults
            override val responseTime: Long = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
            override val countryCode: String
                get() = TODO("Not yet implemented")
            override val category: String
                get() = TODO("Not yet implemented")
            override val sourceId: String
                get() = TODO("Not yet implemented")
            override val query: String
                get() = TODO("Not yet implemented")
        },
        articles = articles.mapList()
    )
}