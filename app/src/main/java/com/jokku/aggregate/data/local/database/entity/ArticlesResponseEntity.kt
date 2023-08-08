package com.jokku.aggregate.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jokku.aggregate.data.local.database.DatabaseConstants.ARTICLE_RESPONSES
import java.util.Date

@Entity(tableName = ARTICLE_RESPONSES)
data class ArticlesResponseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "total_results")
    val totalResults: Int,
    val date: Date,
    @ColumnInfo(name = "country_code")
    val countryCode: String = "",
    val category: String = "",
    @ColumnInfo(name = "source_id")
    val sourceId: String = "",
    val query: String = "",
) {
}