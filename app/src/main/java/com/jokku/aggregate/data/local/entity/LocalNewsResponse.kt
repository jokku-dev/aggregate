package com.jokku.aggregate.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jokku.aggregate.data.local.DatabaseConstants.NEWS_RESPONSE
import com.jokku.aggregate.data.mapper.DataModelMapper
import com.jokku.aggregate.data.mapper.mapList
import com.jokku.aggregate.data.repo.model.NewsResponse
import com.jokku.aggregate.data.repo.model.NewsResponse.OkNewsResponse

@Entity(tableName = NEWS_RESPONSE)
data class LocalNewsResponse(
    @PrimaryKey val id: String,
    val articles: List<LocalArticle>,
    val totalResults: Int,
    val category: String
) : DataModelMapper<NewsResponse> {
    override fun map(): OkNewsResponse {
        return OkNewsResponse(
            articles = articles.mapList(),
            totalResults = totalResults
        )
    }
}