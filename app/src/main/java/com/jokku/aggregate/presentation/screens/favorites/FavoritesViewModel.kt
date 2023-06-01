package com.jokku.aggregate.presentation.screens.favorites

import androidx.lifecycle.viewModelScope
import com.jokku.aggregate.data.repo.DataStoreRepository
import com.jokku.aggregate.data.repo.NewsRepository
import com.jokku.aggregate.presentation.model.UiArticle
import com.jokku.aggregate.presentation.model.UiErrorMessage
import com.jokku.aggregate.presentation.screens.BaseNewsViewModel
import com.jokku.aggregate.domain.Result
import com.jokku.aggregate.presentation.model.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import javax.inject.Inject

interface FavoritesViewModel {
    val favoritesUiState: StateFlow<FavoritesUiState>

    fun getArticlesOfFavoriteCategories(): Job
}

@HiltViewModel
class MainFavoritesViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val dataStoreRepository: DataStoreRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : BaseNewsViewModel(), FavoritesViewModel {

    private val favoritesViewModelState = MutableStateFlow(Result<FavoritesViewModelState>)
    override val favoritesUiState: StateFlow<Result<FavoritesUiState>>
        get() = TODO("Not yet implemented")

    override fun getArticlesOfFavoriteCategories() = viewModelScope.launch(dispatcher) {
        TODO("Not yet implemented")
    }

    private suspend fun getFavoriteCategories(): List<UiCategory> {
        return dataStoreRepository.readFavoriteCategories().toList().flatten()
    }
}


sealed interface FavoritesUiState {

    val isLoading: Boolean
    val errorMessages: List<UiErrorMessage>

    data class NoArticles(
        override val isLoading: Boolean,
        override val errorMessages: List<UiErrorMessage>
    ) : FavoritesUiState

    data class HasArticles(
        val categorisedArticles: List<UiCategoryArticles>,
        val selectedUiArticle: UiArticle,
        val isArticleOpen: Boolean,
        override val isLoading: Boolean,
        override val errorMessages: List<UiErrorMessage>
    ) : FavoritesUiState
}

private data class FavoritesViewModelState(
    val categorisedArticles: List<UiCategoryArticles> = emptyList(),
    val selectedArticleUrl: String? = null,
    val isArticleOpen: Boolean = false,
    val isLoading: Boolean = false,
    val loggedIn: Boolean = false,
    val errorMessages: List<UiErrorMessage> = emptyList()
) {
    fun toUiState(): FavoritesUiState =
        if (categorisedArticles.isEmpty()) {
            FavoritesUiState.NoArticles(
                isLoading = isLoading,
                errorMessages = errorMessages
            )
        } else {
            val articles = categorisedArticles.flatMap { block -> block.articles }
            FavoritesUiState.HasArticles(
                categorisedArticles = categorisedArticles,
                selectedUiArticle = articles.find { article ->
                    article.url == selectedArticleUrl
                } ?: UiArticle(),
                isArticleOpen = isArticleOpen,
                isLoading = isLoading,
                errorMessages = errorMessages
            )
        }
}

@Serializable
data class UiCategory (
    val text: UiText
)

data class UiCategoryArticles(
    val articles: List<UiArticle>,
    val uiCategory: UiCategory
)

data class FavoriteCategory(
    val language: String,
    val country: String?,
    val category: String?,
    val source: String?
)

data class FavoritesRequest(
    val apiKey: String,
    val category: FavoriteCategory,
    val country: String,
    val sources: String,
    val q: String,
    val pageSize: Int,
    val page: Int
)