package com.jokku.aggregate.data.repo.model

import com.jokku.aggregate.data.remote.model.RemoteSource

data class RepositorySourcesResponse(
    val sources: List<RemoteSource>? = null,
    val errorCode: String? = null,
    val errorMessage: String? = null,
)
