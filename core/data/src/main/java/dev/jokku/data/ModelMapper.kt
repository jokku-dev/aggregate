package dev.jokku.data

import dev.jokku.aggregate.data.local.database.entity.ArticleEntity
import dev.jokku.aggregate.data.local.database.entity.ArticlesResponseEntity
import dev.jokku.data.model.Article
import dev.jokku.network.model.NetworkArticle
import dev.jokku.network.model.NetworkArticlesResponse
import kotlin.collections.map

// In multimodular projects it's better to make these mappers as extension functions and place them
// in corresponding modules

interface FromRemoteMapper<To> {
    fun asEntity(): To
}

interface FromEntityMapper<To> {
    fun asExternalModel(): To
}

inline fun <To, reified From : FromRemoteMapper<To>> List<From>.mapList(): List<To> =
    map { it.asEntity() }

inline fun <To, reified From : FromEntityMapper<To>> List<From>.mapList(): List<To> =
    map { it.asExternalModel() }

internal fun NetworkArticle.toArticleEntity() : ArticleEntity {
    TODO()
}

internal fun ArticleEntity.toArticle() : Article {
    TODO()
}

internal fun NetworkArticle.toArticle() : Article {
    TODO()
}

internal fun NetworkArticlesResponse<NetworkArticle>.toArticlesResponseEntity() : ArticlesResponseEntity {
    TODO()
}