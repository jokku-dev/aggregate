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


    fun isLoggedIn() = viewModelScope.launch(dispatcher) {
        dataStoreRepository.readUserLoggedIn().collect { logged ->
            _homeState.update { it.copy(logged = logged) }
        }
    }

    fun selectCategory(selected: Category) = viewModelScope.launch(dispatcher) {
        val categories = _homeState.value.categories.map { category ->
            category.copy(selected = category == selected)
        }
        _homeState.update { state -> state.copy(categories = categories) }
    }

}

data class HomeState(
    val logged: Boolean = false,
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
    val news: List<ArticlePreview> = emptyList()
)

data class Category(
    val text: Int,
    var selected: Boolean = false
)

data class ArticlePreview(
    @DrawableRes val image: Int,
    val title: String,
    val publishedAt: String,
    val bookmarked: Boolean = false
)