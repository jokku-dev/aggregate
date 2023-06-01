package com.jokku.aggregate.data.remote.model

import com.jokku.aggregate.data.mapper.DataModelMapper
import com.jokku.aggregate.data.repo.model.RepositoryArticleSource
import com.jokku.aggregate.presentation.model.UiArticleSource
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
) : DataModelMapper<RepositoryArticleSource> {

    override fun map() = RepositoryArticleSource(
        id = id,
        name = name
    )
}