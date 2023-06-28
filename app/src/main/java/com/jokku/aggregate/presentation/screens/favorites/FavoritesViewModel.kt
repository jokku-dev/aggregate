package com.jokku.aggregate.presentation.screens.favorites

import androidx.lifecycle.viewModelScope
import com.jokku.aggregate.data.UrlParameter
import com.jokku.aggregate.data.repo.NewsRepository
import com.jokku.aggregate.data.repo.PreferencesRepository
import com.jokku.aggregate.domain.TopHeadlinesRequest
import com.jokku.aggregate.presentation.screens.BaseNewsViewModel
import com.jokku.aggregate.presentation.model.UiCategory
import com.jokku.aggregate.presentation.model.CountryCategorisedArticles
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

interface FavoritesViewModel {
    val favoritesUiState: StateFlow<FavoritesUiState>

    fun getArticlesOfFavoriteCategory(
        countries: List<UiCategory>,
        categories: List<UiCategory>
    ): Flow<List<CountryCategorisedArticles>>
}

@HiltViewModel
class DefaultFavoritesViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val preferences: PreferencesRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : BaseNewsViewModel(), FavoritesViewModel {

    @OptIn(ExperimentalCoroutinesApi::class)
    override val favoritesUiState: StateFlow<FavoritesUiState> =
        newsRepository.userData
            .map { userData ->
                if (
                    userData.countryCodes.isEmpty() &&
                    userData.categoryCodes.isEmpty()
                ) {
                    flowOf(FavoritesUiState.Success(emptyList()))
                } else {
                    getArticlesOfFavoriteCategory(
                        userData.countryCodes,
                        userData.categoryCodes
                    ).mapToFavoritesState()
                }
            }
            .flatMapLatest { it }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = FavoritesUiState.Loading
            )

    override fun getArticlesOfFavoriteCategory(
        countries: List<UiCategory>,
        categories: List<UiCategory>
    ): Flow<List<CountryCategorisedArticles>> {
        viewModelScope.launch(dispatcher) {
            countries.forEach { country ->
                categories.forEach { category ->
                    newsRepository.getTopHeadlines(
                        TopHeadlinesRequest.ByCountryAndCategoryRequest(
                            country = (country.code as UrlParameter.CountryCode).value,
                            category = (category.code as UrlParameter.CategoryCode).value
                        )
                    )
                }
            }
        }
    }
}

sealed interface FavoritesUiState {

    object Loading : FavoritesUiState

    data class Success(
        val categorisedArticles: List<CountryCategorisedArticles>
    ) : FavoritesUiState
}

private fun Flow<List<CountryCategorisedArticles>>.mapToFavoritesState(): Flow<FavoritesUiState> =
    map<List<CountryCategorisedArticles>, FavoritesUiState>(FavoritesUiState::Success)
        .onStart { emit(FavoritesUiState.Loading) }