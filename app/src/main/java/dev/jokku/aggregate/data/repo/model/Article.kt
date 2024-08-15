package dev.jokku.aggregate.data.repo.model

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
): ModelMapper<UiArticle> {
    override fun asEntity() = UiArticle(
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
) : ModelMapper<UiArticleSource> {
    override fun asEntity() = UiArticleSource(
        id = id,
        name = name
    )
}