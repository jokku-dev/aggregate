package com.jokku.aggregate.data.remote.model


import com.jokku.aggregate.data.local.database.entity.LocalSource
import com.jokku.aggregate.data.mapper.DataModelMapper
import com.jokku.aggregate.data.repo.model.Source
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteSource(
    /**
     * The type of news to expect from this news source.
     */
    @SerialName("category")
    val category: String,
    /**
     * The country this news source is based in (and primarily writes about).
     */
    @SerialName("country")
    val country: String,
    /**
     * A description of the news source
     */
    @SerialName("description")
    val description: String,
    /**
     * The identifier of the news source. You can use this with our other endpoints.
     */
    @SerialName("id")
    val id: String,
    /**
     * The language that this news source writes in.
     */
    @SerialName("language")
    val language: String,
    /**
     * The name of the news source
     */
    @SerialName("name")
    val name: String,
    /**
     * The URL of the homepage.
     */
    @SerialName("url")
    val url: String
) : DataModelMapper<LocalSource> {

    override fun map() = LocalSource(
        category = category,
        country = country,
        description = description,
        id = id,
        language = language,
        name = name,
        url = url
    )
}