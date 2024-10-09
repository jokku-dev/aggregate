package dev.aggregate.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.aggregate.data.LocalDataProvider
import dev.aggregate.data.repository.NewsRepository
import dev.aggregate.data.repository.PreferencesRepository
import dev.aggregate.model.Article
import dev.aggregate.model.TopCategoryType
import dev.aggregate.model.ui.UiArticle
import dev.aggregate.model.ui.UiCategorisedArticles
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

interface FavoritesViewModel {
    val favoritesUiState: StateFlow<FavoritesState>

    fun getFavoriteCategoryArticles(
        country: String?,
        category: String?,
        topCategoryType: TopCategoryType
    ): Flow<UiCategorisedArticles>
}

// Exceptions from offline first repo is rare but still shall be caught by .catch or .retry
@HiltViewModel
class DefaultFavoritesViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val preferencesRepository: PreferencesRepository,
    private val localDataProvider: LocalDataProvider,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel(), FavoritesViewModel {
    private val bookmarkedArticles = preferencesRepository.userData.map { data ->
        data.bookmarkedArticleIds
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val favoritesUiState: StateFlow<FavoritesState> = preferencesRepository.userData
        .map { userData ->
            getFavoriteCategoryArticles(
                userData.favoriteCountry,
                userData.favoriteCategory,
                userData.topCategoryType
            ).mapToFavoritesState()
        }
        .flatMapLatest { it }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = FavoritesState.Loading()
        )

    override fun getFavoriteCategoryArticles(
        country: String?,
        category: String?,
        topCategoryType: TopCategoryType
    ): Flow<UiCategorisedArticles> = TODO("Not implemented")
//        if (country.isEmpty() && category.isEmpty()) {
//            // Need to add location or at least system language request
//            newsRepository.getTopHeadlines(request = )
//                .mapToUiCategorisedArticles(bookmarkedArticles, localDataProvider)
//        } else {
//            newsRepository.getFavoriteTopHeadlines(countries = countries, categories = categories)
//                .mapToUiCategorisedArticles(bookmarkedArticles, localDataProvider, topCategoryType)
//        }
}

private fun Flow<List<UiArticle>>.mapToUiCategorisedArticles(
    bookmarkedArticleIds: Flow<Set<String>>,
    localDataProvider: LocalDataProvider,
    topCategoryType: TopCategoryType
): Flow<UiCategorisedArticles> {
    val categorisedArticles = mutableListOf<UiCategorisedArticles>()

    TODO("Not implemented")
//    filterNot { it.isEmpty() }
//        .combine(bookmarkedArticleIds) { newsResponses, bookmarkedArticles ->
//            countries.forEach { countryCode ->
//                val byCountryResponses = newsResponses.filter { response ->
//                    response.countryId == countryCode.value
//                }
//            }
//        }
}

private fun Flow<List<Article>>.mapToUiCategorisedArticles(
    bookmarkedArticleIds: Flow<Set<String>>,
    localDataProvider: LocalDataProvider
): Flow<UiCategorisedArticles> {
    TODO("Not implemented")
//    return filterNotNull().map { response ->
//        UiCategorisedArticles(localTopHeadlines = data.mapList())
//    }
}

private fun Flow<UiCategorisedArticles>.mapToFavoritesState(): Flow<FavoritesState> =
    map<UiCategorisedArticles, FavoritesState>(FavoritesState::Success)
        .onStart { emit(FavoritesState.Loading()) }
