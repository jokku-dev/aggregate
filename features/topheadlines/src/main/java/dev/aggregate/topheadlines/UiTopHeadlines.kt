package dev.aggregate.topheadlines

import dev.aggregate.model.ui.UiArticle
import dev.aggregate.model.ui.UiCategory


data class UiTopHeadlines(
    val articles: List<UiArticle> = emptyList(),
    val totalResults: Int = 0,
    val currentTime: Long = 0L,
    val selectedArticle: UiArticle = UiArticle(),
    val isArticleOpen: Boolean = false,
    val favorites: Set<String> = emptySet(),
    val searchInput: String = "",
    val categories: List<UiCategory> = emptyList(),
)
