package dev.aggregate.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkArticlesResponse<E>(
    /**
     * If the request was successful or not. Options: ok, error. In the case of error a code and
     * message property will be populated.
     */
    @SerialName("status") val status: String,
    /**
     * The results of the request.
     */
    @SerialName("articles") val articles: List<E>,
    /**
     * The total number of results available for your request. Only a limited number are shown at a
     * time though, so use the page parameter in your requests to page through them.
     */
    @SerialName("totalResults") val totalResults: Int,
)