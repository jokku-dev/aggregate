package dev.jokku.newsdata.model

import dev.jokku.aggregate.data.mapper.ModelMapper
import dev.jokku.aggregate.presentation.model.UiArticle
import dev.jokku.aggregate.presentation.model.UiArticleSource

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: ArticleSource,
    val title: String,
    val url: String,
    val urlToImage: String
): ModelMapper<dev.jokku.aggregate.presentation.model.UiArticle> {
    override fun asEntity() = dev.jokku.aggregate.presentation.model.UiArticle(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = source.asEntity(),
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}

data class ArticleSource(
    val id: String,
    val name: String
) : ModelMapper<dev.jokku.aggregate.presentation.model.UiArticleSource> {
    override fun asEntity() = dev.jokku.aggregate.presentation.model.UiArticleSource(
        id = id,
        name = name
    )
}