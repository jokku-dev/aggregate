package dev.aggregate.topheadlines

import dev.aggregate.data.RequestResult
import dev.aggregate.model.ArticlesResponse

internal fun RequestResult<UiTopHeadlines>.toState(): TopHeadlinesState {
    return when (this) {
        is RequestResult.Error -> TopHeadlinesState.Failure(data)
        is RequestResult.InProgress -> TopHeadlinesState.Loading(data)
        is RequestResult.Success -> TopHeadlinesState.Success(data)
    }
}

internal fun ArticlesResponse.toUiTopHeadlines() = UiTopHeadlines()
