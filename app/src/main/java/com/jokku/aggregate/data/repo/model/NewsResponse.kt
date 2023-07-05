package com.jokku.aggregate.data.repo.model

import com.jokku.aggregate.data.mapper.DataModelMapper
import com.jokku.aggregate.data.mapper.mapList
import com.jokku.aggregate.presentation.model.UiNewsResponse

data class NewsResponse(
    val id: Int,
    val articles: List<Article>,
    val totalResults: Int,
    val currentTime: Long,
    val isFavorite: Boolean,
    val countryId: String = "",
    val categoryId: String = "",
    val sourceId: String = "",
    val query: String = ""
): DataModelMapper<UiNewsResponse> {
    override fun map() = UiNewsResponse(
        id = id,
        articles = articles.mapList(),
        totalResults = totalResults,
        currentTime = currentTime,
        isFavorite = isFavorite,
        countryId = countryId,
        categoryId = categoryId,
        sourceId = sourceId,
        query = query
    )
}
