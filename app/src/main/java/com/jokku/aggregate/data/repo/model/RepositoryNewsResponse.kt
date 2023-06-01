package com.jokku.aggregate.data.repo.model

import com.jokku.aggregate.data.mapper.DataModelMapper
import com.jokku.aggregate.data.remote.model.RemoteArticle
import com.jokku.aggregate.data.repo.DataSourceType
import com.jokku.aggregate.presentation.model.UiNewsResponse

data class RepositoryNewsResponse(
    val articles: List<RemoteArticle>? = null,
    val totalResults: Int? = null,
    //this error info only occurs in case of wrong request parameters
    val errorCode: String? = null,
    val errorMessage: String? = null
)