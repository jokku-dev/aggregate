package dev.aggregate.database.database.entity.intermediate

import androidx.room.Embedded
import androidx.room.Relation
import dev.aggregate.database.entity.ArticleEntity
import dev.aggregate.database.entity.ArticlesResponseEntity
import dev.aggregate.model.ArticlesResponse

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
    TODO("Not Implemented")
}