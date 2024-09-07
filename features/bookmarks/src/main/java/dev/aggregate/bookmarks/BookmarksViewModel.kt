package dev.aggregate.bookmarks

import dev.aggregate.ui.old.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

interface BookmarksViewModel {
    val bookmarksState: StateFlow<dev.aggregate.app.ui.old.Result<BookmarksState>>
}

@dagger.hilt.android.lifecycle.HiltViewModel
class MainBookmarksViewModel @javax.inject.Inject constructor(

) : dev.aggregate.app.BaseNewsViewModel(), BookmarksViewModel {

    private val _bookmarksState =
        MutableStateFlow<dev.aggregate.app.ui.old.Result<BookmarksState>>(dev.aggregate.app.ui.old.Result.Loading)
    override val bookmarksState = _bookmarksState.asStateFlow()

}

data class BookmarksState(
    val bookmarkedArticles: List<dev.aggregate.app.presentation.model.UiArticle> = emptyList()
)