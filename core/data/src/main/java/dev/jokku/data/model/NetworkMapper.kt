package dev.jokku.data.model

import dev.jokku.aggregate.data.local.database.entity.ArticleEntity
import dev.jokku.aggregate.data.local.database.entity.ArticlesResponseEntity
import dev.jokku.model.Article
import dev.jokku.model.ArticlesResponse
import dev.jokku.network.model.NetworkArticle
import dev.jokku.network.model.NetworkArticlesResponse

fun NetworkArticle.toArticleEntity() : ArticleEntity {
    TODO()
}

fun NetworkArticlesResponse<NetworkArticle>.toArticlesResponseEntity() : ArticlesResponseEntity {
    TODO()
}

fun NetworkArticle.toArticle() : Article {
    TODO()
}

fun NetworkArticlesResponse<NetworkArticle>.toArticlesResponse() : ArticlesResponse {
    TODO()
}