package com.jokku.aggregate.data.remote.model

import com.jokku.aggregate.data.local.database.entity.LocalArticleSource
import com.jokku.aggregate.data.mapper.DataModelMapper
import com.jokku.aggregate.data.repo.model.ArticleSource
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteArticleSource(
    /**
     * Source identifier
     */
    @SerialName("id")
    val id: String,
    /**
     * Source name
     */
    @SerialName("name")
    val name: String
) : DataModelMapper<LocalArticleSource> {

    override fun map() = LocalArticleSource(
        id = id,
        name = name
    )
}