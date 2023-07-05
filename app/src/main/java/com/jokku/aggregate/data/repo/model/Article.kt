package com.jokku.aggregate.data.repo.model

import com.jokku.aggregate.data.mapper.DataModelMapper
import com.jokku.aggregate.presentation.model.UiArticle

data class Article(
    val id: Int,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: ArticleSource,
    val title: String,
    val url: String,
    val urlToImage: String,
    val bookmarked: Boolean
): DataModelMapper<UiArticle> {
    override fun map() = UiArticle(
        id = id,
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = source.map(),
        title = title,
        url = url,
        urlToImage = urlToImage,
        bookmarked = bookmarked
    )
}