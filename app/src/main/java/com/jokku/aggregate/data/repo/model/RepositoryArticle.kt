package com.jokku.aggregate.data.repo.model

import com.jokku.aggregate.data.mapper.DataModelMapper
import com.jokku.aggregate.data.remote.model.RemoteArticleSource
import com.jokku.aggregate.presentation.model.UiArticle

data class RepositoryArticle(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: RepositoryArticleSource,
    val title: String,
    val url: String,
    val urlToImage: String
)