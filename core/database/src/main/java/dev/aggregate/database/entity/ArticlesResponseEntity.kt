package dev.aggregate.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.aggregate.database.utils.DatabaseConstants.ARTICLE_RESPONSES
import java.util.Date

@Entity(tableName = ARTICLE_RESPONSES)
data class ArticlesResponseEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "total_results") val totalResults: Int,
    @ColumnInfo(name = "date") val date: Date,
)