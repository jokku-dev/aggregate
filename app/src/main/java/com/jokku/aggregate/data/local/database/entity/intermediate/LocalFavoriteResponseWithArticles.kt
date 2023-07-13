package com.jokku.aggregate.data.local.database.entity.intermediate

import androidx.room.Embedded
import androidx.room.Relation
import com.jokku.aggregate.data.local.database.entity.LocalArticle
import com.jokku.aggregate.data.local.database.entity.LocalFavoriteTopHeadlinesResponse
import com.jokku.aggregate.data.mapper.DataModelMapper
import com.jokku.aggregate.data.mapper.mapList
import com.jokku.aggregate.data.repo.model.NewsResponse

data class LocalFavoriteResponseWithArticles(
    @Embedded
    val response: LocalFavoriteTopHeadlinesResponse,
    @Relation(
        parentColumn = "responseId",
        entityColumn = "articlesResponseId"
    )
    val articles: List<LocalArticle>
) : DataModelMapper<NewsResponse> {
    override fun map() = NewsResponse(
        articles = articles.mapList(),
        totalResults = response.totalResults,
        currentTime = response.responseTime,
        countryId = response.countryId,
        categoryId = response.categoryId,
        sourceId = response.sourceId,
        query = response.query
    )
}