package com.jokku.aggregate.data.repo.model

import com.jokku.aggregate.data.mapper.DataModelMapper
import com.jokku.aggregate.data.mapper.mapList
import com.jokku.aggregate.presentation.model.UiNewsResponse

data class NewsResponse(
    val articles: List<Article>,
    val totalResults: Int,
    val currentTime: Long,
    val countryId: String = "",
    val categoryId: String = "",
    val sourceId: String = "",
    val query: String = ""
): DataModelMapper<UiNewsResponse> {
    override fun map() = UiNewsResponse(
        articles = articles.mapList(),
        totalResults = totalResults,
        currentTime = currentTime,
        countryId = countryId,
        categoryId = categoryId,
        sourceId = sourceId,
        query = query
    )
}
