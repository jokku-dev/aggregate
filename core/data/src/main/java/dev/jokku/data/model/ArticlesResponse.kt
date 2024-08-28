package dev.jokku.data.model

import java.util.Date

data class ArticlesResponse(
    val articles: List<Article>,
    val totalResults: Int,
    val currentTime: Date,
    val countryId: String = "",
    val categoryId: String = "",
    val sourceId: String = "",
    val query: String = ""
)
