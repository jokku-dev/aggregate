package dev.jokku.aggregate.data.repo.model

data class SourcesResponse(
    val sources: List<Source>,
    val responseTime: Long
)
