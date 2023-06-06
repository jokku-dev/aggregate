package com.jokku.aggregate.presentation.screens.welcome

import androidx.lifecycle.viewModelScope
import com.jokku.aggregate.data.repo.PreferencesRepository
import com.jokku.aggregate.presentation.screens.BaseNewsViewModel
import com.jokku.aggregate.presentation.model.UiOnBoardingPage
import com.jokku.aggregate.presentation.model.UiCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

interface WelcomeViewModel {
    fun setLaunchScreen(screen: String): Job
    fun checkNextPageIsLast(nextPage: UiOnBoardingPage)
    fun loadCategoryState(type: CategoryType): Job
    fun switchIsTopicFavorite(changedCategory: UiCategory)
    fun setFavoriteTopics(categories: List<UiCategory>): Job
}

@HiltViewModel
class MainWelcomeViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : BaseNewsViewModel(), WelcomeViewModel {

    private val _onBoardingState = MutableStateFlow(OnBoardingState())
    val onBoardingState = _onBoardingState.asStateFlow()

    private val _favoriteCategoriesState = MutableStateFlow(FavoriteCategoriesState())
    val favoriteCategoriesState = _favoriteCategoriesState.asStateFlow()

    override fun setLaunchScreen(screen: String) = viewModelScope.launch(dispatcher) {
        preferencesRepository.setLaunchScreen(screen = screen)
    }


    override fun checkNextPageIsLast(nextPage: UiOnBoardingPage) {
        val isLastPage = _onBoardingState.value.pages.last() == nextPage
        _onBoardingState.update { state -> state.copy(isLastPage = isLastPage) }
    }

    override fun loadCategoryState(type: CategoryType): Job {
        preferencesRepository
    }

    override fun switchIsTopicFavorite(changedCategory: UiCategory) {
        val switchedTopics = _favoriteCategoriesState.value.categories.map { currentTopic ->
            if (currentTopic == changedCategory) currentTopic.copy(selected = !currentTopic.selected)
            else currentTopic
        }
        _favoriteCategoriesState.update { state -> state.copy(categories = switchedTopics) }
    }

    override fun setFavoriteTopics(categories: List<UiCategory>, type: CategoryType) = viewModelScope.launch(dispatcher) {
        preferencesRepository.setFavoriteCategories(categories = categories, type = type)
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

