package dev.jokku.newsdata.model

data class SourcesResponse(
    val sources: List<Source>,
    val responseTime: Long
)
