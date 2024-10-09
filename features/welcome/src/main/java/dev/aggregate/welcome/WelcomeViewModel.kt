package dev.aggregate.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.aggregate.common.di.Dispatcher
import dev.aggregate.common.di.NewsDispatchers
import dev.aggregate.data.CategoryType
import dev.aggregate.data.LocalDataProvider
import dev.aggregate.data.repository.PreferencesRepository
import dev.aggregate.model.UserData
import dev.aggregate.model.ui.UiCategories
import dev.aggregate.model.ui.UiCategory
import dev.aggregate.model.ui.UiOnBoardingPage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

interface WelcomeViewModel {
    val onBoardingUiState: StateFlow<OnBoardingState>
    val favoriteCategoriesUiState: StateFlow<UiCategories>
    fun setLaunchScreen(screen: String)
    fun chooseFavoriteTopic(topic: UiCategory?)
    fun changeSelectedTopic(topic: UiCategory)
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
class MainWelcomeViewModel @Inject internal constructor(
    private val preferencesRepository: PreferencesRepository,
    localDataProvider: LocalDataProvider,
    @Dispatcher(NewsDispatchers.MAIN) private val dispatcher: CoroutineDispatcher
) : ViewModel(), WelcomeViewModel {

    private val userData: Flow<UserData> = preferencesRepository.userData
    private val selectedCategoryState: MutableStateFlow<UiCategory?> = MutableStateFlow(null)

    override val onBoardingUiState: StateFlow<OnBoardingState> = flowOf(
        OnBoardingState(localDataProvider.provideOnBoardingPages())
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = OnBoardingState(localDataProvider.provideOnBoardingPages())
    )

    override val favoriteCategoriesUiState: StateFlow<UiCategories> = combine(
        userData,
        flowOf(localDataProvider.provideNewsCategoriesPreferences(CategoryType.CATEGORY)),
        selectedCategoryState
    ) { userData, categories, selected ->
        val categoriesWithSelection = categories.map { category ->
            when {
                userData.favoriteCategory == category.code && selected == null -> {
                    category.copy(selected = true)
                }
                selected == category -> {
                    category.copy(selected = true)
                }
                else -> {
                    category.copy(selected = false)
                }
            }
        }
        UiCategories(
            categories = categoriesWithSelection,
            selectedCategory = categoriesWithSelection.find { category -> category.selected }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = UiCategories(
            categories = localDataProvider.provideNewsCategoriesPreferences(CategoryType.CATEGORY)
        )
    )

    val favoriteCountriesUiState: StateFlow<FavoriteCountriesState> = combine(
        userData,
        flowOf(localDataProvider.provideNewsCategoriesPreferences(CategoryType.COUNTRY))
    ) { userData, countries ->
        FavoriteCountriesState(
            countries = countries.map { country ->
                if (userData.favoriteCountry == country.code) {
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

    override fun setLaunchScreen(screen: String) {
        viewModelScope.launch(dispatcher) {
            preferencesRepository.setLaunchScreen(screen = screen)
        }
    }

    override fun chooseFavoriteTopic(topic: UiCategory?) {
        viewModelScope.launch(dispatcher) {
            preferencesRepository.setFavoriteCategory(topic?.code)
        }
    }

    override fun changeSelectedTopic(topic: UiCategory) {
        selectedCategoryState.value = topic
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
