package dev.aggregate.topheadlines

import dev.aggregate.data.RequestResult
import dev.aggregate.model.ui.UiArticle

internal fun RequestResult<List<UiArticle>>.toState(): TopHeadlinesState {
    return when (this) {
        is RequestResult.Error -> TopHeadlinesState.Failure(data)
        is RequestResult.InProgress -> TopHeadlinesState.Loading(data)
        is RequestResult.Success -> TopHeadlinesState.Success(data)
    }
}
