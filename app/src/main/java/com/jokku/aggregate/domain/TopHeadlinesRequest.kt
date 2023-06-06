package com.jokku.aggregate.domain

sealed interface TopHeadlinesRequest {

    val apiKey: String
    val query: String
    val pageSize: Int
    val page: Int
    data class ByCountryAndCategoryRequest(
        val country: String,
        val category: String,
        override val apiKey: String,
        override val query: String,
        override val pageSize: Int,
        override val page: Int
    ) : TopHeadlinesRequest

    data class BySourceRequest(
        val source: String,
        override val apiKey: String,
        override val query: String,
        override val pageSize: Int,
        override val page: Int
    ) : TopHeadlinesRequest
}

