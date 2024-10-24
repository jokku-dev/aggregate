package dev.aggregate.bookmarks

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import dev.aggregate.model.ui.UiArticle

@Stable
sealed class BookmarksState(open val stateData: List<UiArticle>?) {

    @Immutable
    data object None : BookmarksState(stateData = null)

    @Stable
    class Loading(stateData: List<UiArticle>? = null) : BookmarksState(stateData)

    @Stable
    class Success(override val stateData: List<UiArticle>) : BookmarksState(stateData)

    @Stable
    class Failure(stateData: List<UiArticle>? = null) : BookmarksState(stateData)
}
