package com.jokku.aggregate.presentation.screens.topheadlines

import androidx.lifecycle.viewModelScope
import com.jokku.aggregate.R
import com.jokku.aggregate.presentation.model.UiErrorMessage
import com.jokku.aggregate.data.repo.DataStoreRepository
import com.jokku.aggregate.data.repo.NewsRepository
import com.jokku.aggregate.presentation.model.UiArticle
import com.jokku.aggregate.presentation.screens.BaseNewsViewModel
import com.jokku.aggregate.presentation.screens.favorites.FavoriteCategory
import com.jokku.aggregate.domain.Result
import com.jokku.aggregate.presentation.model.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

interface TopHeadlinesViewModel {
    val topHeadlinesUiState: StateFlow<TopHeadlinesUiState>

    fun refreshArticles()
    fun getSignInStatus(): Job
    fun selectCategory(selected: UiSelectableCategory): Job
}

@HiltViewModel
class MainTopHeadlinesViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val dataStoreRepository: DataStoreRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : BaseNewsViewModel(), TopHeadlinesViewModel {

    private val topHeadlinesViewModelState = MutableStateFlow(TopHeadlinesViewModelState(isLoading = true))
    override val topHeadlinesUiState = topHeadlinesViewModelState
        .map(TopHeadlinesViewModelState::toUiState)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = topHeadlinesViewModelState.value.toUiState()
        )

    init {
        refreshArticles()

        viewModelScope.launch {
            newsRepository.observeRandomArticles().collect { favorites ->
                topHeadlinesViewModelState.update { it.copy(favorites = favorites) }
            }
        }
    }

    override fun refreshArticles() {
        topHeadlinesViewModelState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val result = newsRepository.getFavoriteCategoryArticles()
            topHeadlinesViewModelState.update {
                when (result) {
                    is com.jokku.aggregate.domain.ResultState.Result.Success -> {
                        it.copy(articles = result.data, isLoading = false)
                    }
                    is com.jokku.aggregate.domain.ResultState.Result.Failure -> {
                        val errorMessages = it.errorMessages + UiErrorMessage(
                            id = UUID.randomUUID().mostSignificantBits,
                            text = UiText.StringResource(R.string.can_not_update_latest_news)
                        )
                        it.copy(errorMessages = errorMessages, isLoading = false)
                    }
                }
            }
        }
    }

    override fun getSignInStatus() = viewModelScope.launch(dispatcher) {
        dataStoreRepository.readUserLoggedIn().collect { logged ->
            topHeadlinesViewModelState.update { it.copy(loggedIn = logged) }
        }
    }

    override fun selectCategory(selected: UiSelectableCategory) = viewModelScope.launch(dispatcher) {
        val categories = topHeadlinesViewModelState.value.categories.map { category ->
            category.copy(selected = category == selected)
        }
        topHeadlinesViewModelState.update { state -> state.copy(categories = categories) }
    }


}

sealed interface TopHeadlinesUiState {

    val isLoading: Boolean
    val errorMessages: List<UiErrorMessage>
    val searchInput: String
    val categories: List<UiSelectableCategory>

    data class NoArticles(
        override val isLoading: Boolean,
        override val errorMessages: List<UiErrorMessage>,
        override val searchInput: String,
        override val categories: List<UiSelectableCategory>
    ) : TopHeadlinesUiState

    data class HasArticles(
        val articles: List<UiArticle>,
        val selectedArticle: UiArticle,
        val isArticleOpen: Boolean,
        val favorites: Set<String>,
        override val isLoading: Boolean,
        override val errorMessages: List<UiErrorMessage>,
        override val searchInput: String,
        override val categories: List<UiSelectableCategory>
    ) : TopHeadlinesUiState
}

private data class TopHeadlinesViewModelState(
    val articles: List<UiArticle> = emptyList(),
    val selectedArticleUrl: String? = null,
    val isArticleOpen: Boolean = false,
    val favorites: Set<String> = emptySet(),
    val isLoading: Boolean = false,
    val loggedIn: Boolean = false,
    val categories: List<UiSelectableCategory> = listOf(
        UiSelectableCategory(text = R.string.random, selected = true),
        UiSelectableCategory(text = R.string.business),
        UiSelectableCategory(text = R.string.entertainment),
        UiSelectableCategory(text = R.string.general),
        UiSelectableCategory(text = R.string.health),
        UiSelectableCategory(text = R.string.science),
        UiSelectableCategory(text = R.string.sports),
        UiSelectableCategory(text = R.string.technology)
    ),
    val errorMessages: List<UiErrorMessage> = emptyList(),
    val searchInput: String = ""
) {
    fun toUiState(): TopHeadlinesUiState =
        if (articles.isEmpty()) {
            TopHeadlinesUiState.NoArticles(
                isLoading = isLoading,
                errorMessages = errorMessages,
                searchInput = searchInput,
                categories = categories
            )
        } else {
            TopHeadlinesUiState.HasArticles(
                articles = articles,
                selectedArticle = articles.find {
                    it.url == selectedArticleUrl
                } ?: UiArticle(),
                isArticleOpen = isArticleOpen,
                favorites = favorites,
                isLoading = isLoading,
                errorMessages = errorMessages,
                searchInput = searchInput,
                categories = categories
            )
        }
}

data class TopHeadlinesRequest(
    val apiKey: String,
    val category: FavoriteCategory,
    val q: String,
    val pageSize: Int,
    val page: Int
)