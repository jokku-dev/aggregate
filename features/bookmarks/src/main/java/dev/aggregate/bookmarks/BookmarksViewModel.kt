package dev.aggregate.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.aggregate.data.RequestResult
import dev.aggregate.data.repository.NewsPreferencesRepository
import dev.aggregate.model.ui.UiArticle
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

interface BookmarksViewModel {
    val bookmarksState: StateFlow<BookmarksState>
}

@HiltViewModel
class MainBookmarksViewModel @Inject constructor(
    val newsPreferencesRepository: NewsPreferencesRepository,
) : ViewModel(), BookmarksViewModel {

    override val bookmarksState: StateFlow<BookmarksState> =
        newsPreferencesRepository.observeBookmarkedArticles()
            .map(RequestResult<List<UiArticle>>::toState)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = BookmarksState.None
            )
}
