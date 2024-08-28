package dev.jokku.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.jokku.database.utils.DatabaseConstants.SOURCE_RESPONSES
import java.util.Date

@Entity(tableName = SOURCE_RESPONSES)
data class SourcesResponseEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "date") val date: Date,
)
