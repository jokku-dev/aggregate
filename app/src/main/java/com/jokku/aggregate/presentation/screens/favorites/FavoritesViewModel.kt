package com.jokku.aggregate.presentation.screens.favorites

import androidx.lifecycle.viewModelScope
import com.jokku.aggregate.data.CategoryCode
import com.jokku.aggregate.data.CountryCode
import com.jokku.aggregate.data.local.preferences.model.TopCategoryType
import com.jokku.aggregate.data.mapper.mapList
import com.jokku.aggregate.data.repo.NewsRepository
import com.jokku.aggregate.data.repo.PreferencesRepository
import com.jokku.aggregate.data.repo.model.ArticlesResponse
import com.jokku.aggregate.data.sync.SyncStatusMonitor
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
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

interface FavoritesViewModel {
    val favoritesUiState: StateFlow<FavoritesState>

    fun getFavoriteCategoryArticles(
        countries: Set<CountryCode>,
        categories: Set<CategoryCode>,
        topCategoryType: TopCategoryType
    ): Flow<UiCategorisedArticles>
}

// Exceptions from offline first repo is rare but still shall be caught by .catch or .retry
@HiltViewModel
class DefaultFavoritesViewModel @Inject constructor(
    syncStatusMonitor: SyncStatusMonitor,
    private val newsRepository: NewsRepository,
    private val preferencesRepository: PreferencesRepository,
    private val localDataProvider: LocalDataProvider,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : BaseNewsViewModel(), FavoritesViewModel {

    private val bookmarkedArticles = preferencesRepository.userData.map { data ->
        data.bookmarkedArticleIds
    }

    val isSyncing = syncStatusMonitor.isSyncing
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )

    @OptIn(ExperimentalCoroutinesApi::class)
    override val favoritesUiState: StateFlow<FavoritesState> = preferencesRepository.userData
        .map { userData ->
            getFavoriteCategoryArticles(
                userData.countryCodes,
                userData.categoryCodes,
                userData.topCategoryType
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
        categories: Set<CategoryCode>,
        topCategoryType: TopCategoryType
    ): Flow<UiCategorisedArticles> =
        if (countries.isEmpty() && categories.isEmpty()) {
            // Need to add location or at least system language request
            newsRepository.getLocalTopHeadlines(country = CountryCode.RU)
                .mapToUiCategorisedArticles(bookmarkedArticles, localDataProvider)
        } else {
            newsRepository.getFavoriteTopHeadlines(countries = countries, categories = categories)
                .mapToUiCategorisedArticles(bookmarkedArticles, localDataProvider, topCategoryType)
        }
}

sealed interface FavoritesState {
    data object Loading : FavoritesState
    data class Success(val categorisedArticles: UiCategorisedArticles) : FavoritesState
}

private fun Flow<List<ArticlesResponse>>.mapToUiCategorisedArticles(
    bookmarkedArticleIds: Flow<Set<String>>,
    localDataProvider: LocalDataProvider,
    topCategoryType: TopCategoryType
): Flow<UiCategorisedArticles> {
    val categorisedArticles = mutableListOf<UiCategorisedArticles>()

    filterNot { it.isEmpty() }
        .combine(bookmarkedArticleIds) { newsResponses, bookmarkedArticles ->
            countries.forEach { countryCode ->
                val byCountryResponses = newsResponses.filter { response ->
                    response.countryId == countryCode.value
                }

            }
        }
}

private fun Flow<ArticlesResponse>.mapToUiCategorisedArticles(
    bookmarkedArticleIds: Flow<Set<String>>,
    localDataProvider: LocalDataProvider
): Flow<UiCategorisedArticles> {
    return filterNotNull().map { response ->
        UiCategorisedArticles(
            localTopHeadlines = response.articles.mapList()
        )
    }
}

private fun Flow<UiCategorisedArticles>.mapToFavoritesState(): Flow<FavoritesState> =
    map<UiCategorisedArticles, FavoritesState>(FavoritesState::Success)
        .onStart { emit(FavoritesState.Loading) }