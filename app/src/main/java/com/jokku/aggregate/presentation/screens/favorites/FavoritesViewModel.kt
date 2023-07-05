package com.jokku.aggregate.presentation.screens.favorites

import androidx.lifecycle.viewModelScope
import com.jokku.aggregate.data.CategoryCode
import com.jokku.aggregate.data.CountryCode
import com.jokku.aggregate.data.local.database.entity.LocalNewsResponse
import com.jokku.aggregate.data.repo.NewsRepository
import com.jokku.aggregate.data.repo.PreferencesRepository
import com.jokku.aggregate.data.repo.model.NewsResponse
import com.jokku.aggregate.presentation.model.UiCategorisedArticles
import com.jokku.aggregate.presentation.screens.BaseNewsViewModel
import com.jokku.aggregate.presentation.screens.welcome.LocalDataProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

interface FavoritesViewModel {
    val favoritesUiState: StateFlow<FavoritesState>

    fun getFavoriteCategoryArticles(
        countries: Set<CountryCode> = emptySet(),
        categories: Set<CategoryCode> = emptySet()
    ): Flow<List<UiCategorisedArticles>>
}

@HiltViewModel
class DefaultFavoritesViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val preferencesRepository: PreferencesRepository,
    private val localDataProvider: LocalDataProvider,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : BaseNewsViewModel(), FavoritesViewModel {

    private val bookmarkedArticles = preferencesRepository.userData.map { data ->
        data.bookmarkedArticleIds
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val favoritesUiState: StateFlow<FavoritesState> = preferencesRepository.userData
        .map { userData ->
            getFavoriteCategoryArticles(
                userData.countryCodes,
                userData.categoryCodes
            ).mapToFavoritesState()
        }
        .flatMapLatest { it }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = FavoritesState.Loading
        )

    override fun getFavoriteCategoryArticles(
        countries: Set<CountryCode>,
        categories: Set<CategoryCode>
    ): Flow<List<UiCategorisedArticles>> =
        if (countries.isEmpty() && categories.isEmpty()) {
            newsRepository.getTopHeadlines()
        } else {
            newsRepository.getFavoriteTopHeadlines(countries, categories)
        }.mapToUiCategorisedArticles(bookmarkedArticles, localDataProvider, countries, categories)
}

sealed interface FavoritesState {
    object Loading : FavoritesState
    data class Success(val categorisedArticles: List<UiCategorisedArticles>) : FavoritesState
}

private fun Flow<List<LocalNewsResponse>>.mapToUiCategorisedArticles(
    bookmarkedArticleIds: Flow<Set<String>>,
    localDataProvider: LocalDataProvider,
    countries: Set<CountryCode>,
    categories: Set<CategoryCode>
): Flow<List<UiCategorisedArticles>> {
    val categorisedArticles = mutableListOf<UiCategorisedArticles>()
    filterNot { it.isEmpty() }
        .combine(bookmarkedArticleIds) { newsResponses, bookmarkedArticles ->
            countries.forEach { code ->
                val byCountryResponses = newsResponses.filter { response ->
                    response.countryId == code.value
                }

            }
        }
}

private fun Flow<List<UiCategorisedArticles>>.mapToFavoritesState(): Flow<FavoritesState> =
    map<List<UiCategorisedArticles>, FavoritesState>(FavoritesState::Success)
        .onStart { emit(FavoritesState.Loading) }