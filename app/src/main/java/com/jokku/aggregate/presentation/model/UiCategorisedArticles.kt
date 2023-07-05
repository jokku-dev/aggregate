package com.jokku.aggregate.presentation.model

sealed interface UiCategorisedArticles

data class UiTopCategoryArticles(
    val topCategory: UiText,
    val articles: List<UiCategoryArticles>
) : UiCategorisedArticles

data class UiCategoryArticles(
    val category: UiText,
    val articles: List<UiArticle>
) : UiCategorisedArticles