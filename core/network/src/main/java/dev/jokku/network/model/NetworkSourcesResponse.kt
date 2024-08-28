package dev.jokku.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkSourcesResponse<E>(
    /**
     * The results of the request.
     */
    @SerialName("sources") val sources: List<E>,
    /**
     * If the request was successful or not. Options: ok, error. In the case of error a code and
     * message property will be populated.
     */
    @SerialName("status") val status: String,
)