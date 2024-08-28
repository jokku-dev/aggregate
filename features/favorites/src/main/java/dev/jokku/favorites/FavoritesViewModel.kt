package dev.jokku.favorites

import androidx.lifecycle.viewModelScope
import dev.jokku.aggregate.presentation.model.UiCategorisedArticles
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
import kotlin.collections.filter

interface FavoritesViewModel {
    val favoritesUiState: StateFlow<FavoritesState>

    fun getFavoriteCategoryArticles(
        countries: Set<dev.jokku.data.CountryCode>,
        categories: Set<dev.jokku.data.CategoryCode>,
        topCategoryType: dev.jokku.database.preferences.model.TopCategoryType
    ): Flow<dev.jokku.aggregate.presentation.model.UiCategorisedArticles>
}

// Exceptions from offline first repo is rare but still shall be caught by .catch or .retry
@dagger.hilt.android.lifecycle.HiltViewModel
class DefaultFavoritesViewModel @javax.inject.Inject constructor(
    syncStatusMonitor: dev.jokku.data.sync.SyncStatusMonitor,
    private val newsRepository: dev.jokku.data.NewsRepository,
    private val preferencesRepository: dev.jokku.data.PreferencesRepository,
    private val localDataProvider: dev.jokku.aggregate.presentation.screens.welcome.LocalDataProvider,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : dev.jokku.aggregate.BaseNewsViewModel(), FavoritesViewModel {

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
        countries: Set<dev.jokku.data.CountryCode>,
        categories: Set<dev.jokku.data.CategoryCode>,
        topCategoryType: dev.jokku.database.preferences.model.TopCategoryType
    ): Flow<dev.jokku.aggregate.presentation.model.UiCategorisedArticles> =
        if (countries.isEmpty() && categories.isEmpty()) {
            // Need to add location or at least system language request
            newsRepository.getLocalTopHeadlines(country = dev.jokku.data.CountryCode.RU)
                .mapToUiCategorisedArticles(bookmarkedArticles, localDataProvider)
        } else {
            newsRepository.getFavoriteTopHeadlines(countries = countries, categories = categories)
                .mapToUiCategorisedArticles(bookmarkedArticles, localDataProvider, topCategoryType)
        }
}

sealed interface FavoritesState {
    data object Loading : FavoritesState
    data class Success(val categorisedArticles: dev.jokku.aggregate.presentation.model.UiCategorisedArticles) : FavoritesState
}

private fun Flow<List<dev.jokku.data.model.ArticlesResponse>>.mapToUiCategorisedArticles(
    bookmarkedArticleIds: Flow<Set<String>>,
    localDataProvider: dev.jokku.aggregate.presentation.screens.welcome.LocalDataProvider,
    topCategoryType: dev.jokku.database.preferences.model.TopCategoryType
): Flow<dev.jokku.aggregate.presentation.model.UiCategorisedArticles> {
    val categorisedArticles = mutableListOf<dev.jokku.aggregate.presentation.model.UiCategorisedArticles>()

    filterNot { it.isEmpty() }
        .combine(bookmarkedArticleIds) { newsResponses, bookmarkedArticles ->
            countries.forEach { countryCode ->
                val byCountryResponses = newsResponses.filter { response ->
                    response.countryId == countryCode.value
                }

            }
        }
}

private fun Flow<dev.jokku.data.model.ArticlesResponse>.mapToUiCategorisedArticles(
    bookmarkedArticleIds: Flow<Set<String>>,
    localDataProvider: dev.jokku.aggregate.presentation.screens.welcome.LocalDataProvider
): Flow<dev.jokku.aggregate.presentation.model.UiCategorisedArticles> {
    return filterNotNull().map { response ->
        dev.jokku.aggregate.presentation.model.UiCategorisedArticles(
            localTopHeadlines = dev.jokku.data.mapList()
        )
    }
}

private fun Flow<dev.jokku.aggregate.presentation.model.UiCategorisedArticles>.mapToFavoritesState(): Flow<FavoritesState> =
    map<dev.jokku.aggregate.presentation.model.UiCategorisedArticles, FavoritesState>(FavoritesState::Success)
        .onStart { emit(FavoritesState.Loading) }