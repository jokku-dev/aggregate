package dev.aggregate.data.repository

import dev.aggregate.data.RequestResult
import dev.aggregate.data.util.TopHeadlinesRequest
import dev.aggregate.model.ui.UiArticle
import dev.aggregate.model.ui.UiArticlesResponse
import dev.aggregate.model.ui.toUiArticles
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface NewsPreferencesRepository {
    fun observeAll(request: TopHeadlinesRequest): Flow<RequestResult<List<UiArticle>>>
    fun observeBookmarkedArticles(): Flow<RequestResult<List<UiArticle>>>
}

class CompositeNewsPreferencesRepository @Inject constructor(
    val newsRepository: NewsRepository,
    val preferencesRepository: PreferencesRepository,
) : NewsPreferencesRepository {

    override fun observeAll(request: TopHeadlinesRequest): Flow<RequestResult<UiArticlesResponse>> =
        newsRepository.getTopHeadlines(request)
            .combine(preferencesRepository.userData) { result, userData ->
                when (result) {
                    is RequestResult.Error -> RequestResult.Error(result.data?.articles.toUiArticles(userData))
                    is RequestResult.InProgress -> RequestResult.InProgress(result.data?.articles.toUiArticles(userData))
                    is RequestResult.Success -> RequestResult.Success(result.data.articles.toUiArticles(userData))
                }
            }

    override fun observeBookmarkedArticles(): Flow<RequestResult<UiArticlesResponse>> =
        preferencesRepository.userData
            .map { it.bookmarkedArticleIds }
            .distinctUntilChanged()
            .flatMapLatest { bookmarkedArticleIds ->
                when {
                    bookmarkedArticleIds.isEmpty() -> emptyFlow()
                    else -> observeAll(TopHeadlinesRequest())
                }
            }
}

inline fun <T : Any, R : Any> RequestResult<T>.map(transform: (T) -> R): RequestResult<R> {
    return when (this) {
        is RequestResult.Success -> RequestResult.Success(transform(this.data))
        is RequestResult.Error -> RequestResult.Error(transform(this.data))
        is RequestResult.InProgress -> RequestResult.InProgress(transform(this.data))
    }
}
