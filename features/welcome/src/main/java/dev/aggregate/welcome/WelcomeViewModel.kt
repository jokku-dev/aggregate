package dev.aggregate.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.aggregate.data.CategoryType
import dev.aggregate.data.LocalDataProvider
import dev.aggregate.data.repository.PreferencesRepository
import dev.aggregate.model.UserData
import dev.aggregate.model.ui.UiCategory
import dev.aggregate.model.ui.UiOnBoardingPage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

interface WelcomeViewModel {
    val onBoardingUiState: StateFlow<OnBoardingState>
    val favoriteCategoriesUiState: StateFlow<FavoriteCategoriesState>
    fun setLaunchScreen(screen: String)
//    fun switchIsCountryFavorite(
//        country: String,
//        preferred: Boolean
//    )

//    fun switchIsCategoryFavorite(
//        category: String,
//        preferred: Boolean
//    )
}

@HiltViewModel
class MainWelcomeViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
    localDataProvider: LocalDataProvider,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel(), WelcomeViewModel {

    private val userData: Flow<UserData> = preferencesRepository.userData

    override val onBoardingUiState: StateFlow<OnBoardingState> = flowOf(
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
                if (userData.countryCode?.name == country.code) {
                    country.copy(selected = true)
                } else {
                    country.copy(selected = false)
                }
            }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = FavoriteCountriesState(
            localDataProvider.provideNewsCategoriesPreferences(CategoryType.COUNTRY)
        )
    )

    override val favoriteCategoriesUiState: StateFlow<FavoriteCategoriesState> = combine(
        userData,
        flowOf(localDataProvider.provideNewsCategoriesPreferences(CategoryType.CATEGORY))
    ) { userData, categories ->
        FavoriteCategoriesState(
            categories = categories.map { category ->
                if (userData.categoryCode?.name == category.code) {
                    category.copy(selected = true)
                } else {
                    category.copy(selected = false)
                }
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

//    override fun switchIsCountryFavorite(
//        country: String,
//        preferred: Boolean
//    ) {
//        viewModelScope.launch(dispatcher) {
//            preferencesRepository.togglePreferredCountries(
//                country,
//                preferred
//            )
//        }
//    }

//    override fun switchIsCategoryFavorite(
//        category: String,
//        preferred: Boolean
//    ) {
//        viewModelScope.launch(dispatcher) {
//            preferencesRepository.togglePreferredCategories(
//                category,
//                preferred
//            )
//        }
//    }
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
