package dev.jokku.topheadlines.old

sealed interface Result<out T> {
    data class Success<out T>(
        val actualData: T
    ) : Result<T>

    data class Failure<out T>(
        val message: dev.jokku.aggregate.presentation.model.UiErrorMessage,
        val cachedData: T?
    ) : Result<T>
}