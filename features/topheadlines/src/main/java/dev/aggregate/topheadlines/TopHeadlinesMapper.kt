package dev.aggregate.topheadlines

import dev.aggregate.data.RequestResult
import dev.aggregate.model.ui.UiArticle
import kotlinx.collections.immutable.toImmutableList

internal fun RequestResult<List<UiArticle>>.toState(): TopHeadlinesState {
    return when (this) {
        is RequestResult.Error -> TopHeadlinesState.Error(data?.toImmutableList())
        is RequestResult.InProgress -> TopHeadlinesState.InProgress(data?.toImmutableList())
        is RequestResult.Success -> TopHeadlinesState.Success(data.toImmutableList())
    }
}
