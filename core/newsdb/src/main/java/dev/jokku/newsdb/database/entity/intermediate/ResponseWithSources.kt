package dev.jokku.newsdb.database.entity.intermediate

import androidx.room.Embedded
import androidx.room.Relation

data class ResponseWithSources(
    @Embedded
    val response: dev.jokku.aggregate.data.local.database.entity.SourcesResponseEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "sources_id"
    )
    val sources: List<dev.jokku.aggregate.data.local.database.entity.SourceEntity>
)