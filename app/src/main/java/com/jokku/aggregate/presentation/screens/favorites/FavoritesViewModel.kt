package com.jokku.aggregate.presentation.screens.favorites

import androidx.lifecycle.viewModelScope
import com.jokku.aggregate.data.repo.NewsRepository
import com.jokku.aggregate.presentation.model.UiArticle
import com.jokku.aggregate.presentation.screens.BaseNewsViewModel
import com.jokku.aggregate.domain.Result
import com.jokku.aggregate.presentation.model.UiCategory
import com.jokku.aggregate.presentation.model.UiCategoryArticles
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import javax.inject.Inject

interface FavoritesViewModel {
    val favoritesState: StateFlow<Result<FavoritesState>>

    fun getArticlesOfFavoriteCategories()
}

@HiltViewModel
class DefaultFavoritesViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : BaseNewsViewModel(), FavoritesViewModel {

    private val _favoritesState = MutableStateFlow<Result<FavoritesState>>(Result.Loading)
    override val favoritesState = _favoritesState.asStateFlow()

    override fun getArticlesOfFavoriteCategories() {
        viewModelScope.launch(dispatcher) {
            TODO("Not yet implemented")
        }
    }

    private suspend fun getFavoriteCategories(): List<UiCategory> {
        return preferencesRepository.readFavoriteCategories().toList().flatten()
    }
}

data class FavoritesState(
    val categorisedArticles: List<UiCategoryArticles>,
    val selectedUiArticle: UiArticle,
    val isArticleOpen: Boolean,
)