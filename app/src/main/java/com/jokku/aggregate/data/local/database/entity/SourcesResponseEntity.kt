package com.jokku.aggregate.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jokku.aggregate.data.local.database.DatabaseConstants.SOURCE_RESPONSES
import java.util.Date

@Entity(tableName = SOURCE_RESPONSES)
data class SourcesResponseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val date: Date
)
