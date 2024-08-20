package dev.jokku.ui

data class UiNewsResponse(
    val articles: List<dev.jokku.ui.UiArticle>,
    val totalResults: Int,
    val currentTime: Long,
    val countryId: String = "",
    val categoryId: String = "",
    val sourceId: String = "",
    val query: String = ""
)