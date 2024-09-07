package dev.aggregate.database.entity.intermediate

import androidx.room.Embedded
import androidx.room.Relation
import dev.aggregate.database.entity.SourceEntity
import dev.aggregate.database.entity.SourcesResponseEntity

data class ResponseWithSources(
    @Embedded
    val response: SourcesResponseEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "sources_id"
    )
    val sources: List<SourceEntity>,
)
