package dev.jokku.topheadlines.old

data class SourcesRequest(
    val apiKey: String,
    val category: String,
    val language: String,
    val country: String
)