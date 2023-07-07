package com.jokku.aggregate.data.repo.model

import com.jokku.aggregate.data.mapper.DataModelMapper
import com.jokku.aggregate.presentation.model.UiArticle

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: ArticleSource,
    val title: String,
    val url: String,
    val urlToImage: String
): DataModelMapper<UiArticle> {
    override fun map() = UiArticle(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = source.map(),
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}