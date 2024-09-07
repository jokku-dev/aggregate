package dev.aggregate.data

import dev.aggregate.database.entity.ArticleEntity
import dev.aggregate.database.entity.ArticlesResponseEntity
import dev.aggregate.model.Article
import dev.aggregate.model.ArticlesResponse
import dev.aggregate.network.model.NetworkArticle
import dev.aggregate.network.model.NetworkArticlesResponse

fun NetworkArticle.toArticleEntity(): ArticleEntity {
    TODO("Not Implemented")
}

fun NetworkArticlesResponse<NetworkArticle>.toArticlesResponseEntity(): ArticlesResponseEntity {
    TODO("Not Implemented")
}

fun NetworkArticle.toArticle(): Article {
    TODO("Not Implemented")
}

fun NetworkArticlesResponse<NetworkArticle>.toArticlesResponse(): ArticlesResponse {
    TODO("Not Implemented")
}
