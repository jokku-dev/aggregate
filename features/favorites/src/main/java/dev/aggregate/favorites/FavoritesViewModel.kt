package dev.aggregate.favorites

import androidx.lifecycle.viewModelScope
import dev.aggregate.presentation.model.UiCategorisedArticles
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

interface FavoritesViewModel {
    val favoritesUiState: StateFlow<FavoritesState>

    fun getFavoriteCategoryArticles(
        countries: Set<dev.aggregate.app.data.CountryCode>,
        categories: Set<dev.aggregate.app.data.CategoryCode>,
        topCategoryType: dev.aggregate.app.database.preferences.model.TopCategoryType
    ): Flow<dev.aggregate.app.presentation.model.UiCategorisedArticles>
}

// Exceptions from offline first repo is rare but still shall be caught by .catch or .retry
@dagger.hilt.android.lifecycle.HiltViewModel
class DefaultFavoritesViewModel @javax.inject.Inject constructor(
    syncStatusMonitor: dev.aggregate.app.data.sync.SyncStatusMonitor,
    private val newsRepository: dev.aggregate.app.data.NewsRepository,
    private val preferencesRepository: dev.aggregate.app.data.PreferencesRepository,
    private val localDataProvider: dev.aggregate.app.presentation.screens.welcome.LocalDataProvider,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : dev.aggregate.app.BaseNewsViewModel(), FavoritesViewModel {

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
        countries: Set<dev.aggregate.app.data.CountryCode>,
        categories: Set<dev.aggregate.app.data.CategoryCode>,
        topCategoryType: dev.aggregate.app.database.preferences.model.TopCategoryType
    ): Flow<dev.aggregate.app.presentation.model.UiCategorisedArticles> =
        if (countries.isEmpty() && categories.isEmpty()) {
            // Need to add location or at least system language request
            newsRepository.getLocalTopHeadlines(country = dev.aggregate.app.data.CountryCode.RU)
                .mapToUiCategorisedArticles(bookmarkedArticles, localDataProvider)
        } else {
            newsRepository.getFavoriteTopHeadlines(countries = countries, categories = categories)
                .mapToUiCategorisedArticles(bookmarkedArticles, localDataProvider, topCategoryType)
        }
}

sealed interface FavoritesState {
    data object Loading : FavoritesState
    data class Success(val categorisedArticles: dev.aggregate.app.presentation.model.UiCategorisedArticles) :
        FavoritesState
}

private fun Flow<List<dev.aggregate.app.data.model.ArticlesResponse>>.mapToUiCategorisedArticles(
    bookmarkedArticleIds: Flow<Set<String>>,
    localDataProvider: dev.aggregate.app.presentation.screens.welcome.LocalDataProvider,
    topCategoryType: dev.aggregate.app.database.preferences.model.TopCategoryType
): Flow<dev.aggregate.app.presentation.model.UiCategorisedArticles> {
    val categorisedArticles =
        mutableListOf<dev.aggregate.app.presentation.model.UiCategorisedArticles>()

    filterNot { it.isEmpty() }
        .combine(bookmarkedArticleIds) { newsResponses, bookmarkedArticles ->
            countries.forEach { countryCode ->
                val byCountryResponses = newsResponses.filter { response ->
                    response.countryId == countryCode.value
                }

            }
        }
}

private fun Flow<dev.aggregate.app.data.model.ArticlesResponse>.mapToUiCategorisedArticles(
    bookmarkedArticleIds: Flow<Set<String>>,
    localDataProvider: dev.aggregate.app.presentation.screens.welcome.LocalDataProvider
): Flow<dev.aggregate.app.presentation.model.UiCategorisedArticles> {
    return filterNotNull().map { response ->
        dev.aggregate.app.presentation.model.UiCategorisedArticles(
            localTopHeadlines = dev.aggregate.app.data.mapList()
        )
    }
}

private fun Flow<dev.aggregate.app.presentation.model.UiCategorisedArticles>.mapToFavoritesState(): Flow<FavoritesState> =
    map<dev.aggregate.app.presentation.model.UiCategorisedArticles, FavoritesState>(FavoritesState::Success)
        .onStart { emit(FavoritesState.Loading) }