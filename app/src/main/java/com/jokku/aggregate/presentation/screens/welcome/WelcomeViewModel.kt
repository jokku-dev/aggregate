package com.jokku.aggregate.presentation.screens.welcome

import androidx.lifecycle.viewModelScope
import com.jokku.aggregate.data.local.preferences.PreferencesDataSource
import com.jokku.aggregate.data.repo.NewsRepository
import com.jokku.aggregate.presentation.screens.BaseNewsViewModel
import com.jokku.aggregate.presentation.model.UiOnBoardingPage
import com.jokku.aggregate.presentation.model.UiCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

interface WelcomeViewModel {
    fun setLaunchScreen(screen: String)
    fun checkNextPageIsLast(nextPage: UiOnBoardingPage)
    fun loadCategoryState(type: CategoryType)
    fun switchIsTopicFavorite(changedCategory: UiCategory)
    fun setFavoriteTopics(categories: List<UiCategory>, type: CategoryType)
}

@HiltViewModel
class MainWelcomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : BaseNewsViewModel(), WelcomeViewModel {

    private val launchScreen: Flow<String> =
        newsRepository.

    private val onBoardingState = MutableStateFlow(OnBoardingState())
    val onBoardingUiState = onBoardingState.asStateFlow()

    private val _favoriteCategoriesState = MutableStateFlow(FavoriteCategoriesState())
    val favoriteCategoriesState = _favoriteCategoriesState.asStateFlow()

    override fun setLaunchScreen(screen: UiCategory) {
        viewModelScope.launch(dispatcher) {
            newsRepository.setLaunchScreen(screen = screen)
        }
    }

    override fun checkNextPageIsLast(nextPage: UiOnBoardingPage) {
        val isLastPage = onBoardingState.value.pages.last() == nextPage
        onBoardingState.update { state -> state.copy(isLastPage = isLastPage) }
    }

    override fun loadCategoryState(type: CategoryType) {
        newsRepository
    }

    override fun switchIsTopicFavorite(changedCategory: UiCategory) {
        val switchedTopics = _favoriteCategoriesState.value.categories.map { currentTopic ->
            if (currentTopic == changedCategory) currentTopic.copy(selected = !currentTopic.selected)
            else currentTopic
        }
        _favoriteCategoriesState.update { state -> state.copy(categories = switchedTopics) }
    }

    override fun setFavoriteTopics(categories: List<UiCategory>, type: CategoryType) {
        viewModelScope.launch(dispatcher) {
            newsRepository.setFavoriteCategories(categories = categories, type = type)
        }
    }
}

data class OnBoardingState(
    val pages: List<UiOnBoardingPage> = emptyList(),
    val isLastPage: Boolean = false
)

data class FavoriteCategoriesState(
    val languages: List<UiCategory> = emptyList(),
    val categories: List<UiCategory> = emptyList(),
    val countries: List<UiCategory> = emptyList(),
    val sources: List<UiCategory> = emptyList()
)

