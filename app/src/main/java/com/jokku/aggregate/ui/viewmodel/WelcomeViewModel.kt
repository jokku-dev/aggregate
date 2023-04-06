package com.jokku.aggregate.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jokku.aggregate.R
import com.jokku.aggregate.data.DataStoreRepository
import com.jokku.aggregate.ui.entity.OnBoardingPage
import com.jokku.aggregate.ui.entity.Topic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val repository: DataStoreRepository
) : ViewModel() {

    private val _onBoardingState = MutableStateFlow(OnBoardingState())
    val onBoardingState = _onBoardingState.asStateFlow()

    private val _favoriteTopicsState = MutableStateFlow(FavoriteTopicsState())
    val favoriteTopicsState = _favoriteTopicsState.asStateFlow()


    fun setLaunchScreen(screen: String) = viewModelScope.launch {
        repository.setLaunchScreen(screen = screen)
    }


    fun checkNextPageIsLast(nextPage: OnBoardingPage) {
        val isLastPage = _onBoardingState.value.pages.last() == nextPage
        _onBoardingState.update { state -> state.copy(isLastPage = isLastPage) }
    }


    fun changeIsTopicFavorite(changedTopic: Topic) {
        val changedTopics = _favoriteTopicsState.value.topics.map { currentTopic ->
            if (currentTopic == changedTopic) currentTopic.copy(selected = !currentTopic.selected)
            else currentTopic
        }
        _favoriteTopicsState.update { state -> state.copy(topics = changedTopics) }
    }

    fun setFavoriteTopics(topics: List<Topic>) = viewModelScope.launch {
        repository.setFavoriteTopics(topics)
    }

    fun getFavoriteTopics() = viewModelScope.launch {
        repository.readFavoriteTopics().collect { topics ->
            _favoriteTopicsState.update { it.copy(topics = topics) }
        }
    }
}

data class FavoriteTopicsState(
    val topics: List<Topic> = emptyList()
)

data class OnBoardingState(
    val pages: List<OnBoardingPage> = listOf(
        OnBoardingPage(
            R.drawable.img_onboarding,
            R.string.on_board_first_title,
            R.string.on_board_first_description
        ),
        OnBoardingPage(
            R.drawable.img_onboarding,
            R.string.on_board_second_title,
            R.string.on_board_second_description
        ),
        OnBoardingPage(
            R.drawable.img_onboarding,
            R.string.on_board_third_title,
            R.string.on_board_third_description
        )
    ),
    val isLastPage: Boolean = false
)