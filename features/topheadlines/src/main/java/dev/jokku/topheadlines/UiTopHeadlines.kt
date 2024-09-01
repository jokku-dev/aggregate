package dev.jokku.topheadlines

import dev.jokku.ui.UiArticle
import dev.jokku.ui.UiCategory

data class UiTopHeadlines(
    val articles: List<UiArticle> = emptyList<UiArticle>(),
    val totalResults: Int = 0,
    val currentTime: Long = 0L,
    val selectedArticle: UiArticle = UiArticle(),
    val isArticleOpen: Boolean = false,
    val favorites: Set<String> = emptySet<String>(),
    val searchInput: String = "",
    val categories: List<UiCategory> = emptyList<UiCategory>(),
)