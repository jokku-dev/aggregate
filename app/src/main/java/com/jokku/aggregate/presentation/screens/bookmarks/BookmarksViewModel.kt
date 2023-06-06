package com.jokku.aggregate.presentation.screens.bookmarks

import com.jokku.aggregate.domain.Result
import com.jokku.aggregate.presentation.model.UiArticle
import com.jokku.aggregate.presentation.screens.BaseNewsViewModel
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