package dev.aggregate.topheadlines

import android.R
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.aggregate.data.map
import dev.aggregate.data.repository.NewsRepository
import dev.aggregate.datastore.PreferencesDataSource
import dev.aggregate.model.ui.UiCategory
import dev.aggregate.model.ui.UiErrorMessage
import dev.aggregate.model.ui.UiText
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject
import javax.inject.Provider
import kotlin.plus

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
    private val preferencesDataSource: PreferencesDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : TopHeadlinesViewModel, ViewModel() {

    override val topHeadlinesState: StateFlow<TopHeadlinesState> =
        getTopHeadlineArticlesUseCase.get().invoke(searchQuery = "")
            .map { request -> request.toState() }
            .stateIn(viewModelScope, SharingStarted.Lazily, TopHeadlinesState.None)

    init {
        refreshArticles()

        viewModelScope.launch {
            getTopHeadlineArticlesUseCase.observeRandomArticles().collect { favorites ->
                topHeadlinesState.update { it.copy(favorites = favorites) }
            }
        }
    }

    override fun refreshArticles() {
        topHeadlinesState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val result = getTopHeadlineArticlesUseCase.getFavoriteTopHeadlines()
            topHeadlinesState.update {
                when (result) {
                    is ResultState.Result.Success -> {
                        it.copy(articles = result.data, isLoading = false)
                    }

                    is ResultState.Result.Failure -> {
                        val errorMessages = it.errorMessages + UiErrorMessage(
                            id = UUID.randomUUID().mostSignificantBits,
                            text = UiText.StringResource(R.string.can_not_update_latest_news)
                        )
                        it.copy(errorMessages = errorMessages, isLoading = false)
                    }
                }
            }
        }
    }

    override fun getSignInStatus() = viewModelScope.launch(dispatcher) {
        preferencesDataSource.readUserLoggedIn().collect { logged ->
            topHeadlinesState.update { it.copy(loggedIn = logged) }
        }
    }

    override fun selectCategory(selected: UiCategory) = viewModelScope.launch(dispatcher) {
        val categories = topHeadlinesState.value.categories.map { category ->
            category.copy(selected = category == selected)
        }
        topHeadlinesState.update { state -> state.copy(categories = categories) }
    }


}
