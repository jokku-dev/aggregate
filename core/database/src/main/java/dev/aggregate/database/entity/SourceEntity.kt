package dev.aggregate.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import dev.aggregate.database.utils.DatabaseConstants.SOURCES

@Entity(
    tableName = SOURCES,
    foreignKeys = [
        ForeignKey(
            entity = SourcesResponseEntity::class,
            parentColumns = ["id"],
            childColumns = ["sources_id"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        )
    ]
)
data class SourceEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "sources_id") val sourcesId: Long,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "country") val country: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "language") val language: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "url") val url: String,
)
