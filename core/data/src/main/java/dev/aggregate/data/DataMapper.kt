package dev.aggregate.data

import dev.aggregate.database.entity.TopHeadlinesArticleEntity
import dev.aggregate.model.Article
import dev.aggregate.network.model.NetworkArticle

fun NetworkArticle.toEntity(): TopHeadlinesArticleEntity {
    TODO("Not Implemented")
}

fun NetworkArticle.toArticle(): Article {
    TODO("Not Implemented")
}

fun TopHeadlinesArticleEntity.toArticle(): Article {
    TODO("Not Implemented")
}


fun List<NetworkArticle>.toEntity(): List<TopHeadlinesArticleEntity> {
    TODO("Not Implemented")
}

fun List<NetworkArticle>.toArticle(): List<Article> {
    TODO("Not Implemented")
}

fun List<TopHeadlinesArticleEntity>.toArticle(): List<Article> {
    TODO("Not Implemented")
}
