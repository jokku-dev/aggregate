package com.jokku.aggregate.ui.viewmodel

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jokku.aggregate.R
import com.jokku.aggregate.data.repo.DataStoreRepository
import com.jokku.aggregate.data.repo.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val dataStoreRepository: DataStoreRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState = _homeState.asStateFlow()

    private val _bookmarksState = MutableStateFlow(BookmarksState())
    val bookmarksState = _bookmarksState.asStateFlow()

    private val _preferencesState = MutableStateFlow(PreferencesState())
    val profileState = _preferencesState.asStateFlow()


    fun isSignedIn() = viewModelScope.launch(dispatcher) {
        dataStoreRepository.readUserLoggedIn().collect { logged ->
            _homeState.update { it.copy(loggedIn = logged) }
        }
    }

    fun changeSignedIn() = viewModelScope.launch(dispatcher) {

    }

    fun selectCategory(selected: Category) = viewModelScope.launch(dispatcher) {
        val categories = _homeState.value.categories.map { category ->
            category.copy(selected = category == selected)
        }
        _homeState.update { state -> state.copy(categories = categories) }
    }

}

data class HomeState(
    val loggedIn: Boolean = false,
    val categories: List<Category> = listOf(
        Category(text = R.string.random, selected = true),
        Category(text = R.string.business),
        Category(text = R.string.entertainment),
        Category(text = R.string.general),
        Category(text = R.string.health),
        Category(text = R.string.science),
        Category(text = R.string.sports),
        Category(text = R.string.technology)
    ),
    val articles: List<Article> = emptyList()
)

data class Category(
    val text: Int,
    var selected: Boolean = false
)

data class Article(
    val sourceName: String = "",
    val author: String = "",
    val title: String = "",
    val url: String = "",
    @DrawableRes val image: Int = 0,
    val publishedAt: String = "",
    val bookmarked: Boolean = false
)


data class BookmarksState(
    val bookmarkedArticles: List<Article> = emptyList()
)

data class PreferencesState(
    val signedIn: Boolean = false,
    val notifications: Boolean = false
)