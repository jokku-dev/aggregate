package dev.jokku.database.database.entity.intermediate

import androidx.room.Embedded
import androidx.room.Relation
import dev.jokku.aggregate.data.local.database.entity.ArticleEntity
import dev.jokku.aggregate.data.local.database.entity.ArticlesResponseEntity
import dev.jokku.model.ArticlesResponse

data class ResponseWithArticles(
    @Embedded
    val response: ArticlesResponseEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "articles_id"
    )
    val articles: List<ArticleEntity>,
)

fun ResponseWithArticles.toArticlesResponse() : ArticlesResponse {
    TODO()
}