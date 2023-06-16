package com.jokku.aggregate.data.local.database.entity

import com.jokku.aggregate.data.mapper.DataModelMapper
import com.jokku.aggregate.data.repo.model.ArticleSource

data class LocalArticleSource(
    val id: String,
    val name: String
) : DataModelMapper<ArticleSource> {

    override fun map() = ArticleSource(
        id = id,
        name = name
    )
}
