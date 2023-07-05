package com.jokku.aggregate.data.repo.model

import com.jokku.aggregate.data.mapper.DataModelMapper
import com.jokku.aggregate.presentation.model.UiArticleSource

data class ArticleSource(
    val id: String,
    val name: String
) : DataModelMapper<UiArticleSource> {
    override fun map() = UiArticleSource(
        id = id,
        name = name
    )
}