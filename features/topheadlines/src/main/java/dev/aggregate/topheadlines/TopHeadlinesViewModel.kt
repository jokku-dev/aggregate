package dev.aggregate.topheadlines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.aggregate.common.di.Dispatcher
import dev.aggregate.common.di.NewsDispatchers
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
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

interface TopHeadlinesViewModel {
    val topHeadlinesState: StateFlow<TopHeadlinesState>

    fun refreshArticles()
    fun getSignInStatus(): Job
    fun selectCategory(selected: UiCategory): Job
}

@HiltViewModel
class MainTopHeadlinesViewModel @Inject internal constructor(
    private val getTopHeadlineArticlesUseCase: Provider<GetTopHeadlineArticlesUseCase>,
    private val newsRepository: NewsRepository,
    private val preferencesRepository: PreferencesRepository,
    @Dispatcher(NewsDispatchers.MAIN) private val dispatcher: CoroutineDispatcher
) : TopHeadlinesViewModel, ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    override val topHeadlinesState: StateFlow<TopHeadlinesState> =
        combine(_searchQuery, preferencesRepository.userData) { query, userData ->
            query to userData
        }
            .flatMapLatest { (query, userData) ->
                getTopHeadlineArticlesUseCase.get().invoke(query, userData)
                    .map { result -> result.toState() }
            }
            .stateIn(
                viewModelScope,
                SharingStarted.Lazily,
                TopHeadlinesState.None
            )

    init {
        refreshArticles()
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

    override fun selectCategory(selected: UiCategory) = viewModelScope.launch(dispatcher) {
        TODO("Not implemented")
//        val categories = topHeadlinesState.value.categories.map { category ->
//            category.copy(selected = category == selected)
//        }
//        topHeadlinesState.update { state -> state.copy(categories = categories) }
    }
}
