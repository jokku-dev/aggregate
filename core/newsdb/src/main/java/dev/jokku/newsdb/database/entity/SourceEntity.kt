package dev.jokku.newsdb.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import dev.jokku.aggregate.data.local.database.DatabaseConstants.SOURCES
import dev.jokku.aggregate.data.repo.model.Source

@Entity(
    tableName = SOURCES,
    foreignKeys = [
        ForeignKey(
            entity = dev.jokku.aggregate.data.local.database.entity.SourcesResponseEntity::class,
            parentColumns = ["id"],
            childColumns = ["sources_id"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        )
    ]
)
data class SourceEntity(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "sources_id")
    val sourcesId: Long,
    val category: String,
    val country: String,
    val description: String,
    val language: String,
    val name: String,
    val url: String
) : dev.jokku.newsdata.FromEntityMapper<dev.jokku.aggregate.data.repo.model.Source> {
    override fun asExternalModel() = dev.jokku.aggregate.data.repo.model.Source(
        category = category,
        country = country,
        description = description,
        id = id,
        language = language,
        name = name,
        url = url
    )
}
