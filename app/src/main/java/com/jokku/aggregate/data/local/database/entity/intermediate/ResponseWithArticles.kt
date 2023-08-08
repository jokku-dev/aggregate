package com.jokku.aggregate.data.local.database.entity.intermediate

import androidx.room.Embedded
import androidx.room.Relation
import com.jokku.aggregate.data.local.database.entity.ArticleEntity
import com.jokku.aggregate.data.local.database.entity.ArticlesResponseEntity

data class ResponseWithArticles(
    @Embedded
    val response: ArticlesResponseEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "articles_id"
    )
    val articles: List<ArticleEntity>
)
