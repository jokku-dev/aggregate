package dev.aggregate.model.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class SearchIn {
    @SerialName("title")
    TITLE,

    @SerialName("description")
    DESCRIPTION,

    @SerialName("content")
    CONTENT,
}
