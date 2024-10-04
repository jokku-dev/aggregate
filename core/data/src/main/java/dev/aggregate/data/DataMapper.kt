package dev.aggregate.data

import dev.aggregate.database.entity.ArticleEntitySource
import dev.aggregate.database.entity.TopHeadlinesArticleEntity
import dev.aggregate.model.Article
import dev.aggregate.model.ArticleSource
import dev.aggregate.network.model.NetworkArticle
import dev.aggregate.network.model.NetworkArticleSource

fun NetworkArticle.toEntity(): TopHeadlinesArticleEntity {
    return TopHeadlinesArticleEntity(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = source?.toEntity(),
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}

fun NetworkArticle.toArticle(): Article {
    return Article(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = source?.toSource(),
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}

fun TopHeadlinesArticleEntity.toArticle(): Article {
    return Article(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = source?.toSource(),
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}

fun NetworkArticleSource.toEntity(): ArticleEntitySource {
    return ArticleEntitySource(
        id = id ?: name,
        name = name
    )
}

fun NetworkArticleSource.toSource(): ArticleSource {
    return ArticleSource(
        id = id ?: name,
        name = name
    )
}

fun ArticleEntitySource.toSource(): ArticleSource {
    return ArticleSource(
        id = id,
        name = name
    )
}
