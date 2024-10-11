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
import dev.aggregate.model.ui.UiCategory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
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
    val categoriesState: StateFlow<List<UiCategory>>

    fun getSignInStatus(): Job
    fun selectCategory(selectedCategory: UiCategory): Job
    fun addSearchQuery(query: String): Job
}

@HiltViewModel
class MainTopHeadlinesViewModel @Inject internal constructor(
    private val getTopHeadlineArticlesUseCase: Provider<GetTopHeadlineArticlesUseCase>,
    private val newsRepository: NewsRepository,
    private val preferencesRepository: PreferencesRepository,
    private val localDataProvider: LocalDataProvider,
    @Dispatcher(NewsDispatchers.MAIN) private val dispatcher: CoroutineDispatcher
) : TopHeadlinesViewModel, ViewModel() {
    private val searchQuery: MutableStateFlow<String?> = MutableStateFlow(null)
    private val selectedCategoryState: MutableStateFlow<UiCategory?> = MutableStateFlow(null)

    override val categoriesState: StateFlow<List<UiCategory>> = combine(
        preferencesRepository.userData,
        flowOf(localDataProvider.provideNewsCategories(CategoryType.CATEGORY)),
        selectedCategoryState
    ) { userData, categories, selected ->
        categories.map { category ->
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
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    override val topHeadlinesState: StateFlow<TopHeadlinesState> = combine(
        searchQuery,
        categoriesState
    ) { query, categories ->
        val selectedCategory = categories.find { category -> category.selected }?.code
        getTopHeadlineArticlesUseCase.get().invoke(query, selectedCategory)
    }.flatMapLatest { resultFlow ->
        resultFlow.map { result -> result.toState() }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = TopHeadlinesState.None
    )

    override fun selectCategory(selectedCategory: UiCategory) = viewModelScope.launch(dispatcher) {
        selectedCategoryState.update { selectedCategory }
    }

    override fun addSearchQuery(query: String) = viewModelScope.launch(dispatcher) {
        searchQuery.update { query }
    }

    override fun getSignInStatus() = viewModelScope.launch(dispatcher) {
        TODO("Not implemented")
    }
}
