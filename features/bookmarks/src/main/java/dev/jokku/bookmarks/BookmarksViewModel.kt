package dev.jokku.bookmarks

import dev.jokku.ui.old.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

interface BookmarksViewModel {
    val bookmarksState: StateFlow<dev.jokku.ui.old.Result<BookmarksState>>
}

@dagger.hilt.android.lifecycle.HiltViewModel
class MainBookmarksViewModel @javax.inject.Inject constructor(

) : dev.jokku.aggregate.BaseNewsViewModel(), BookmarksViewModel {

    private val _bookmarksState = MutableStateFlow<dev.jokku.ui.old.Result<BookmarksState>>(dev.jokku.ui.old.Result.Loading)
    override val bookmarksState = _bookmarksState.asStateFlow()

}

data class BookmarksState(
    val bookmarkedArticles: List<dev.jokku.aggregate.presentation.model.UiArticle> = emptyList()
)