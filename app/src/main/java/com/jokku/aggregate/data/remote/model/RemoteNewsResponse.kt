package com.jokku.aggregate.data.remote.model
import com.jokku.aggregate.data.local.database.entity.LocalNewsResponse
import com.jokku.aggregate.data.mapper.DataModelMapper
import com.jokku.aggregate.data.mapper.mapList
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName

@Serializable
data class RemoteNewsResponse(
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
    val articles: List<RemoteArticle>,
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
) : DataModelMapper<LocalNewsResponse> {

    override fun map() = LocalNewsResponse(
        articles = articles.mapList(),
        totalResults = totalResults
    )
}