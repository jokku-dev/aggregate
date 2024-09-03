package dev.aggregate.network.model

import kotlinx.serialization.SerialName

@Serializable
data class NetworkSource(
    /**
     * The type of news to expect from this news source.
     */
    @SerialName("category") val category: String,
    /**
     * The country this news source is based in (and primarily writes about).
     */
    @SerialName("country") val country: String,
    /**
     * A description of the news source
     */
    @SerialName("description") val description: String,
    /**
     * The identifier of the news source. You can use this with our other endpoints.
     */
    @SerialName("id") val id: String,
    /**
     * The language that this news source writes in.
     */
    @SerialName("language") val language: String,
    /**
     * The name of the news source
     */
    @SerialName("name") val name: String,
    /**
     * The URL of the homepage.
     */
    @SerialName("url") val url: String
)