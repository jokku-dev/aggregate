package dev.jokku.topheadlines

//noinspection SuspiciousImport
import android.R
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jokku.aggregate.data.local.preferences.PreferencesDataSource
import dev.jokku.data.repository.NewsRepository
import dev.jokku.ui.UiCategory
import dev.jokku.ui.UiErrorMessage
import dev.jokku.ui.UiText
import dev.jokku.ui.old.ByCountryAndCategoryRequest
import dev.jokku.ui.old.TopHeadlinesRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
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
    val uiTopHeadlines: StateFlow<UiTopHeadlines>

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

    private val topHeadlinesViewModelState: StateFlow<TopHeadlinesViewModelState> =
        getTopHeadlineArticlesUseCase.get().invoke()
            .map { it.toState() }
            .stateIn(viewModelScope, SharingStarted.Lazily, TopHeadlinesViewModelState.None)

    override val uiTopHeadlines = topHeadlinesViewModelState
        .map(TopHeadlinesViewModelState::toUiState)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = topHeadlinesViewModelState.value.toUiState()
        )

    init {
        refreshArticles()

        viewModelScope.launch {
            getTopHeadlineArticlesUseCase.observeRandomArticles().collect { favorites ->
                topHeadlinesViewModelState.update { it.copy(favorites = favorites) }
            }
        }
    }

    override fun refreshArticles() {
        topHeadlinesViewModelState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val result = getTopHeadlineArticlesUseCase.getFavoriteTopHeadlines()
            topHeadlinesViewModelState.update {
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
            topHeadlinesViewModelState.update { it.copy(loggedIn = logged) }
        }
    }

    override fun selectCategory(selected: UiCategory) = viewModelScope.launch(dispatcher) {
        val categories = topHeadlinesViewModelState.value.categories.map { category ->
            category.copy(selected = category == selected)
        }
        topHeadlinesViewModelState.update { state -> state.copy(categories = categories) }
    }


}