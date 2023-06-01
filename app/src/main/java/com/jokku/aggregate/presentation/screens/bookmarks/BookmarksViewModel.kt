package com.jokku.aggregate.presentation.screens.bookmarks

import com.jokku.aggregate.presentation.model.UiArticle
import com.jokku.aggregate.presentation.model.UiArticleSource
import com.jokku.aggregate.presentation.screens.BaseNewsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

interface BookmarksViewModel {
    val bookmarksState: StateFlow<BookmarksState>
}

@HiltViewModel
class MainBookmarksViewModel @Inject constructor(

) : BaseNewsViewModel(), BookmarksViewModel {

    private val _bookmarksState = MutableStateFlow(BookmarksState())
    override val bookmarksState = _bookmarksState.asStateFlow()

}

data class BookmarksState(
    val bookmarkedArticles: List<UiArticle> = listOf(
        UiArticle(
            source = UiArticleSource(name = "Source Name"),
            author = "Name Surname",
            title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt",
            publishedAt = "2023-04-25T08:36",
            bookmarked = false
        ),
        UiArticle(
            source = UiArticleSource(name = "Source Name"),
            author = "Name Surname",
            title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt",
            publishedAt = "2023-04-25T08:36",
            bookmarked = false
        ),
        UiArticle(
            source = UiArticleSource(name = "Source Name"),
            author = "Name Surname",
            title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt",
            publishedAt = "2023-04-25T08:36",
            bookmarked = false
        ),
    )
)