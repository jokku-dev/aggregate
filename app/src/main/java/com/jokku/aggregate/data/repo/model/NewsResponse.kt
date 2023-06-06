package com.jokku.aggregate.data.repo.model

sealed interface NewsResponse {

    data class OkNewsResponse(
        val articles: List<Article>,
        val totalResults: Int
    ) : NewsResponse
    //this error response only occurs in case of wrong request parameters
    data class ErrorNewsResponse(
        val errorCode: String,
        val errorMessage: String
    ) : NewsResponse
}
