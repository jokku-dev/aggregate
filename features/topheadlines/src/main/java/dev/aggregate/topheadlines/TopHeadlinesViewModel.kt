package dev.aggregate.topheadlines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.aggregate.common.di.Dispatcher
import dev.aggregate.common.di.NewsDispatchers
import dev.aggregate.data.CategoryType
import dev.aggregate.data.LocalDataProvider
import dev.aggregate.data.repository.NewsRepository
import dev.aggregate.data.repository.PreferencesRepository
import dev.aggregate.model.UserData
import dev.aggregate.model.ui.UiCategories
import dev.aggregate.model.ui.UiCategory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

interface TopHeadlinesViewModel {
    val topHeadlinesState: StateFlow<TopHeadlinesState>
    val categoriesState: StateFlow<UiCategories>

    fun refreshArticles()
    fun getSignInStatus(): Job
    fun selectCategory(selectedCategory: UiCategory): Job
}

@HiltViewModel
class MainTopHeadlinesViewModel @Inject internal constructor(
    private val getTopHeadlineArticlesUseCase: Provider<GetTopHeadlineArticlesUseCase>,
    private val newsRepository: NewsRepository,
    private val preferencesRepository: PreferencesRepository,
    localDataProvider: LocalDataProvider,
    @Dispatcher(NewsDispatchers.MAIN) private val dispatcher: CoroutineDispatcher
) : TopHeadlinesViewModel, ViewModel() {

    private val userData: Flow<UserData> = preferencesRepository.userData
    private val searchQuery: MutableStateFlow<String?> = MutableStateFlow(null)
    private val selectedCategoryState: MutableStateFlow<UiCategory?> = MutableStateFlow(null)
    override val categoriesState: StateFlow<UiCategories> = combine(
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
    override val topHeadlinesState: StateFlow<TopHeadlinesState> = combine(
        searchQuery,
        categoriesState
    ) { query, categories ->
        val selectedCategory = categories.categories.find { category -> category.selected }?.code
        getTopHeadlineArticlesUseCase.get().invoke(query, selectedCategory)
    }.flatMapLatest { resultFlow ->
        resultFlow.map { result -> result.toState() }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        TopHeadlinesState.None
    )

    init {
//        refreshArticles()
    }

    override fun selectCategory(selectedCategory: UiCategory) = viewModelScope.launch(dispatcher) {
        selectedCategoryState.update { selectedCategory }
    }

    override fun refreshArticles() {
        TODO("Not implemented")
//        topHeadlinesState.update { it.copy(isLoading = true) }
//        viewModelScope.launch {
//            val result = getTopHeadlineArticlesUseCase.get()
//            topHeadlinesState.update {
//                when (result) {
//                    is ResultState.Result.Success -> {
//                        it.copy(articles = result.data, isLoading = false)
//                    }
//
//                    is ResultState.Result.Failure -> {
//                        val errorMessages = it.errorMessages + UiErrorMessage(
//                            id = UUID.randomUUID().mostSignificantBits,
//                            text = UiText.StringResource(R.string.can_not_update_latest_news)
//                        )
//                        it.copy(errorMessages = errorMessages, isLoading = false)
//                    }
//                }
//            }
//        }
    }

    override fun getSignInStatus() = viewModelScope.launch(dispatcher) {
        TODO("Not implemented")
//        preferencesDataSource.readUserLoggedIn().collect { logged ->
//            topHeadlinesState.update { it.copy(loggedIn = logged) }
//        }
    }
}
