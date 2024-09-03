package dev.aggregate.welcome

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.code
import kotlin.collections.any

interface WelcomeViewModel {
    fun setLaunchScreen(screen: String)
    fun switchIsCategoryFavorite(categoryCode: dev.aggregate.app.data.UrlParameter, preferred: Boolean)
}

@dagger.hilt.android.lifecycle.HiltViewModel
class MainWelcomeViewModel @javax.inject.Inject constructor(
    private val preferencesRepository: dev.aggregate.app.data.PreferencesRepository,
    localDataProvider: LocalDataProvider,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : dev.aggregate.app.BaseNewsViewModel(), WelcomeViewModel {

    private val userData: Flow<dev.aggregate.app.database.preferences.model.UserData> =
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

    override fun switchIsCategoryFavorite(categoryCode: dev.aggregate.app.data.UrlParameter, preferred: Boolean) {
        viewModelScope.launch(dispatcher) {
            when (categoryCode) {
                is dev.aggregate.app.data.CountryCode -> preferencesRepository.togglePreferredCountries(
                    categoryCode,
                    preferred
                )

                is dev.aggregate.app.data.CategoryCode -> preferencesRepository.togglePreferredCategories(
                    categoryCode,
                    preferred
                )

                else -> return@launch
            }
        }
    }
}

data class OnBoardingState(
    val pages: List<dev.aggregate.app.presentation.model.UiOnBoardingPage> = emptyList()
)

data class FavoriteCountriesState(
    val countries: List<dev.aggregate.app.presentation.model.UiCategory> = emptyList()
)

data class FavoriteCategoriesState(
    val categories: List<dev.aggregate.app.presentation.model.UiCategory> = emptyList()
)