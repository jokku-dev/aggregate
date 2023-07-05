package com.jokku.aggregate.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.jokku.aggregate.data.local.database.DatabaseConstants.NEWS_RESPONSES
import com.jokku.aggregate.data.mapper.DataModelMapper
import com.jokku.aggregate.data.mapper.mapList
import com.jokku.aggregate.data.repo.model.NewsResponse

@Entity(tableName = NEWS_RESPONSES)
data class LocalNewsResponse(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @Relation(
        parentColumn = "id",
        entityColumn = "article_id"
    )
    val articles: List<LocalArticle>,
    val totalResults: Int,
    val currentTime: Long,
    val isFavorite: Boolean,
    val countryId: String = "",
    val categoryId: String = "",
    val sourceId: String = "",
    val query: String = ""
) : DataModelMapper<NewsResponse> {

    override fun map() = NewsResponse(
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