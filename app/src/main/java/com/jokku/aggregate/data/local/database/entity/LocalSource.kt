package com.jokku.aggregate.data.local.database.entity

import com.jokku.aggregate.data.mapper.DataModelMapper
import com.jokku.aggregate.data.repo.model.Source
import kotlinx.serialization.Serializable

@Serializable
data class LocalSource(
    val id: String,
    val category: String,
    val country: String,
    val description: String,
    val language: String,
    val name: String,
    val url: String
) : DataModelMapper<Source> {

    override fun map() = Source(
        category = category,
        country = country,
        description = description,
        id = id,
        language = language,
        name = name,
        url = url
    )
}
