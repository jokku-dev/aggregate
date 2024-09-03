package dev.aggregate.model

data class SourcesResponse(
    val sources: List<Source>,
    val responseTime: Long
)
