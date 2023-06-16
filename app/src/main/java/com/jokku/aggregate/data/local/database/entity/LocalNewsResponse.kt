package com.jokku.aggregate.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import androidx.room.TypeConverters
import com.jokku.aggregate.data.local.database.DatabaseConstants.NEWS_RESPONSES
import com.jokku.aggregate.data.local.database.ListConverter
import com.jokku.aggregate.data.mapper.DataModelMapper
import com.jokku.aggregate.data.mapper.mapList
import com.jokku.aggregate.data.repo.model.NewsResponse
import com.jokku.aggregate.data.repo.model.NewsResponse.OkNewsResponse

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
    val category: String = ""
) : DataModelMapper<NewsResponse> {

    override fun map() = OkNewsResponse(
        articles = articles.mapList(),
        totalResults = totalResults
    )
}