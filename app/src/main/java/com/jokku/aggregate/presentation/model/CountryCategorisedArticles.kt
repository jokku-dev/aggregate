package com.jokku.aggregate.presentation.model

data class CountryCategorisedArticles(
    val country: UiText,
    val articles: List<UiSubcategoryArticles>
)

data class UiSubcategoryArticles(
    val category: UiText,
    val articles: List<UiArticle>
)