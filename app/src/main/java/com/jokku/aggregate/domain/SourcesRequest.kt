package com.jokku.aggregate.domain

data class SourcesRequest(
    val apiKey: String,
    val category: String,
    val language: String,
    val country: String
)