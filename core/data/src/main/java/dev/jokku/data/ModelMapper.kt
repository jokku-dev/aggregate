package dev.jokku.data

import dev.jokku.aggregate.data.local.database.entity.ArticleEntity
import dev.jokku.aggregate.data.local.database.entity.ArticlesResponseEntity
import dev.jokku.database.database.entity.intermediate.ResponseWithArticles
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

