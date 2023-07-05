package com.jokku.aggregate.domain

import com.jokku.aggregate.data.remote.HttpRouts.API_KEY_VALUE

sealed interface TopHeadlinesRequest {
    val apiKey: String
    val query: String
    val pageSize: Int
    val page: Int
}

data class ByCountryAndCategoryRequest(
    val country: String,
    val category: String,
    override val apiKey: String = API_KEY_VALUE,
    override val query: String = "",
    override val pageSize: Int = 20,
    override val page: Int = 1
) : TopHeadlinesRequest

data class BySourceRequest(
    val source: String,
    override val apiKey: String = API_KEY_VALUE,
    override val query: String = "",
    override val pageSize: Int = 20,
    override val page: Int = 1
) : TopHeadlinesRequest