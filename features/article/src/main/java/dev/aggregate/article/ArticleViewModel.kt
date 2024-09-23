package dev.aggregate.article

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.aggregate.article.navigation.ArticleRoute
import dev.aggregate.data.repository.NewsRepository
import dev.aggregate.data.repository.PreferencesRepository
import dev.aggregate.model.ui.UiArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

interface ArticleViewModel {
    val topicId: String
    val articleUiState: StateFlow<ArticleUiState>

//    fun setChosenArticle(uiArticle: UiArticle)
    fun bookmarkArticle(uiArticle: UiArticle, bookmarked: Boolean)

}

@HiltViewModel
class BaseArticleViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    preferencesRepository: PreferencesRepository,
    newsRepository: NewsRepository
) : ViewModel(), ArticleViewModel {

    override val topicId = savedStateHandle.toRoute<ArticleRoute>().id

    override val articleUiState: StateFlow<ArticleUiState> = newsUiState(
        topicId = topicId,
        preferencesRepository = preferencesRepository,
        newsRepository = newsRepository,
    )
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ArticleUiState.Loading,
        )

//    override fun setChosenArticle(uiArticle: UiArticle) {
//        _articleUiState.update { state -> state.copy(article = uiArticle) }
//    }

    override fun bookmarkArticle(uiArticle: UiArticle, bookmarked: Boolean) {
        TODO("Not yet implemented")
    }
}

private fun newsUiState(
    topicId: String,
    preferencesRepository: PreferencesRepository,
    newsRepository: NewsRepository,
): Flow<ArticleUiState> {
    TODO("Not yet implemented")
}

sealed interface ArticleUiState {
    data class Success(val article: UiArticle) : ArticleUiState
    data object Error : ArticleUiState
    data object Loading : ArticleUiState
}
