package com.jokku.aggregate.data.repo.model

sealed interface SourcesResponse {

    data class OkSourcesResponse(
        val sources: List<Source>
    ) : SourcesResponse

    data class ErrorSourcesResponse(
        val errorCode: String,
        val errorMessage: String,
    ) : SourcesResponse
}
