package com.jokku.aggregate.presentation.screens.welcome

import androidx.lifecycle.viewModelScope
import com.jokku.aggregate.data.repo.DataStoreRepository
import com.jokku.aggregate.presentation.screens.BaseNewsViewModel
import com.jokku.aggregate.presentation.model.UiOnBoardingPage
import com.jokku.aggregate.domain.Result
import com.jokku.aggregate.presentation.model.UiSelectableCategory
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
    fun changeIsTopicFavorite(changedCategory: UiSelectableCategory)
    fun setFavoriteTopics(categories: List<UiSelectableCategory>): Job
}

@HiltViewModel
class MainWelcomeViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val welcomeStateProvider: WelcomeStateProvider,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : BaseNewsViewModel(), WelcomeViewModel {

    private val _onBoardingState = MutableStateFlow<Result<OnBoardingState>>(Result.Loading)
    val onBoardingState = _onBoardingState.asStateFlow()

    private val _favoriteCategoriesState = MutableStateFlow<Result<FavoriteCategoriesState>>(Result.Loading)
    val favoriteTopicsState = _favoriteCategoriesState.asStateFlow()


    init {
        val onBoardingState = welcomeStateProvider.createOnBoardingState()
        _onBoardingState.update {  }
        val favoriteCategoriesState = welcomeStateProvider.createFavoriteCategoriesState()


    }

    override fun setLaunchScreen(screen: String) = viewModelScope.launch(dispatcher) {
        dataStoreRepository.setLaunchScreen(screen = screen)
    }


    override fun checkNextPageIsLast(nextPage: UiOnBoardingPage) {
        val isLastPage = _onBoardingState.value.pages.last() == nextPage
        _onBoardingState.update { state -> state.copy(isLastPage = isLastPage) }
    }


    override fun changeIsTopicFavorite(changedCategory: UiSelectableCategory) {
        val changedTopics = _favoriteCategoriesState.value.categories.map { currentTopic ->
            if (currentTopic == changedCategory) currentTopic.copy(selected = !currentTopic.selected)
            else currentTopic
        }
        _favoriteCategoriesState.update { state -> state.copy(categories = changedTopics) }
    }

    override fun setFavoriteTopics(categories: List<UiSelectableCategory>) = viewModelScope.launch(dispatcher) {
        dataStoreRepository.setFavoriteTopics(categories)
    }
}

data class OnBoardingState(
    val pages: List<UiOnBoardingPage> = emptyList(),
    val isLastPage: Boolean = false
)

data class FavoriteCategoriesState(
    val languages: List<UiSelectableCategory> = emptyList(),
    val categories: List<UiSelectableCategory> = emptyList(),
    val countries: List<UiSelectableCategory> = emptyList(),
    val sources: List<UiSelectableCategory> = emptyList()
)

