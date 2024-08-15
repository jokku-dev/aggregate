package dev.jokku.aggregate.presentation.screens.welcome

import androidx.lifecycle.viewModelScope
import dev.jokku.aggregate.data.CategoryCode
import dev.jokku.aggregate.data.CountryCode
import dev.jokku.aggregate.data.UrlParameter
import dev.jokku.aggregate.data.local.preferences.model.UserData
import dev.jokku.aggregate.data.repo.NewsRepository
import dev.jokku.aggregate.data.repo.PreferencesRepository
import dev.jokku.aggregate.presentation.screens.BaseNewsViewModel
import dev.jokku.aggregate.presentation.model.UiOnBoardingPage
import dev.jokku.aggregate.presentation.model.UiCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

interface WelcomeViewModel {
    fun setLaunchScreen(screen: String)
    fun switchIsCategoryFavorite(categoryCode: UrlParameter, preferred: Boolean)
}

@HiltViewModel
class MainWelcomeViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
    localDataProvider: LocalDataProvider,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : BaseNewsViewModel(), WelcomeViewModel {

    private val userData: Flow<UserData> =
        preferencesRepository.userData

    val onBoardingUiState: StateFlow<OnBoardingState> = flowOf(
        OnBoardingState(localDataProvider.provideOnBoardingPages())
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = OnBoardingState(localDataProvider.provideOnBoardingPages())
    )

    val favoriteCountriesUiState: StateFlow<FavoriteCountriesState> = combine(
        userData,
        flowOf(localDataProvider.provideNewsCategoriesPreferences(CategoryType.COUNTRY))
    ) { userData, countries ->
        FavoriteCountriesState(
            countries = countries.map { country ->
                if (userData.countryCodes.any { code -> country.code.equals(code.value) })
                    country.copy(selected = true)
                else
                    country.copy(selected = false)
            }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = FavoriteCountriesState(
            localDataProvider.provideNewsCategoriesPreferences(CategoryType.COUNTRY)
        )
    )

    val favoriteCategoriesUiState: StateFlow<FavoriteCategoriesState> = combine(
        userData,
        flowOf(localDataProvider.provideNewsCategoriesPreferences(CategoryType.CATEGORY))
    ) { userData, categories ->
        FavoriteCategoriesState(
            categories = categories.map { category ->
                if (userData.categoryCodes.any { code -> category.code == code })
                    category.copy(selected = true)
                else
                    category.copy(selected = false)
            }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = FavoriteCategoriesState(
            localDataProvider.provideNewsCategoriesPreferences(CategoryType.CATEGORY)
        )
    )

    override fun setLaunchScreen(screen: String) {
        viewModelScope.launch(dispatcher) {
            preferencesRepository.setLaunchScreen(screen = screen)
        }
    }

    override fun switchIsCategoryFavorite(categoryCode: UrlParameter, preferred: Boolean) {
        viewModelScope.launch(dispatcher) {
            when (categoryCode) {
                is CountryCode -> preferencesRepository.togglePreferredCountries(
                    categoryCode,
                    preferred
                )

                is CategoryCode -> preferencesRepository.togglePreferredCategories(
                    categoryCode,
                    preferred
                )

                else -> return@launch
            }
        }
    }
}

data class OnBoardingState(
    val pages: List<UiOnBoardingPage> = emptyList()
)

data class FavoriteCountriesState(
    val countries: List<UiCategory> = emptyList()
)

data class FavoriteCategoriesState(
    val categories: List<UiCategory> = emptyList()
)