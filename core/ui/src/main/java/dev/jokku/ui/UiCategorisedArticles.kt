package dev.jokku.ui

import kotlin.collections.isNotEmpty

data class UiCategorisedArticles(
    val topCategoryArticles: List<UiTopCategoryArticles> = emptyList(),
    val localTopHeadlines: List<dev.jokku.ui.UiArticle> = emptyList()
) {
    fun favoritesAreNotEmpty() = topCategoryArticles.isNotEmpty()
}

data class UiTopCategoryArticles(
    val category: dev.jokku.aggregate.presentation.model.UiText,
    val articles: List<UiSubcategoryArticles>
)

data class UiSubcategoryArticles(
    val category: dev.jokku.aggregate.presentation.model.UiText,
    val articles: List<dev.jokku.ui.UiArticle>
)