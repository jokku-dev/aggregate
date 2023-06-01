package com.jokku.aggregate.domain

import com.jokku.aggregate.data.repo.DataSourceType
import com.jokku.aggregate.presentation.model.UiErrorMessage

sealed class Result<out T> {

    data class Success<out T>(
        val data: T,
        val source: DataSourceType,
        val remoteErrorMessage: UiErrorMessage? = null
    ) : Result<T>()

    data class Failure<out T>(
        val message: UiErrorMessage,
        val defaultData: T?
    ) : Result<T>()

    object Loading : Result<Nothing>()
}
