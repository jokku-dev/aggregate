package com.jokku.aggregate.presentation.model

data class UiNewsResponse(
    val articles: List<UiArticle> = emptyList(),
    val totalResults: Int = 0
)