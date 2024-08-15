package dev.jokku.aggregate.data.local.database.entity.intermediate

import androidx.room.Embedded
import androidx.room.Relation
import dev.jokku.aggregate.data.local.database.entity.SourceEntity
import dev.jokku.aggregate.data.local.database.entity.SourcesResponseEntity

data class ResponseWithSources(
    @Embedded
    val response: SourcesResponseEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "sources_id"
    )
    val sources: List<SourceEntity>
)