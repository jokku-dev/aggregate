package com.jokku.aggregate.presentation.model

import com.jokku.aggregate.data.repo.model.Article

data class UiNewsResponse(
    val articles: List<UiArticle>,
    val totalResults: Int,
    val currentTime: Long,
    val countryId: String = "",
    val categoryId: String = "",
    val sourceId: String = "",
    val query: String = ""
)