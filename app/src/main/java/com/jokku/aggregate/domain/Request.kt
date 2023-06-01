package com.jokku.aggregate.domain

sealed interface Request {

    data class ByCountryAndCategoryRequest(
        val query: String,
        val country: String,
        val category: String,
    ) : Request

    data class BySourceRequest(
        val query: String,
        val source: String
    ) : Request
}

