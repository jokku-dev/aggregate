package dev.aggregate.bookmarks

import dev.aggregate.data.RequestResult
import dev.aggregate.model.ui.UiArticle

internal fun RequestResult<List<UiArticle>>.toState(): BookmarksState {
    return when (this) {
        is RequestResult.Error -> BookmarksState.Failure(data)
        is RequestResult.InProgress -> BookmarksState.Loading(data)
        is RequestResult.Success -> BookmarksState.Success(data)
    }
}
