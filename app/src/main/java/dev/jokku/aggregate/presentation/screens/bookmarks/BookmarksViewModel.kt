package dev.jokku.aggregate.presentation.screens.bookmarks

import dev.jokku.aggregate.domain.Result
import dev.jokku.aggregate.presentation.model.UiArticle
import dev.jokku.aggregate.presentation.screens.BaseNewsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

interface BookmarksViewModel {
    val bookmarksState: StateFlow<Result<BookmarksState>>
}

@HiltViewModel
class MainBookmarksViewModel @Inject constructor(

) : BaseNewsViewModel(), BookmarksViewModel {

    private val _bookmarksState = MutableStateFlow<Result<BookmarksState>>(Result.Loading)
    override val bookmarksState = _bookmarksState.asStateFlow()

}

data class BookmarksState(
    val bookmarkedArticles: List<UiArticle> = emptyList()
)