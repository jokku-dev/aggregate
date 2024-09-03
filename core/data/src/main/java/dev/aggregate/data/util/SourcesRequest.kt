package dev.aggregate.data.util

data class SourcesRequest(
    val apiKey: String,
    val category: String,
    val language: String,
    val country: String
)