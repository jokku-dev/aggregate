package dev.aggregate.model

import java.util.Date

data class SourcesResponse(
    val sources: List<Source>,
    val responseDate: Date
)
