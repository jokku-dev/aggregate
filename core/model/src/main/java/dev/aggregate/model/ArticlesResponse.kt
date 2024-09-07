package dev.aggregate.model

import java.util.Date

data class ArticlesResponse(
    val articles: List<Article>,
    val totalResults: Int,
    val responseDate: Date
)
