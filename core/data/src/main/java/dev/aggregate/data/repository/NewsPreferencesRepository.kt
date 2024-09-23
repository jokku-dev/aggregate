package dev.aggregate.data.repository

import dev.aggregate.data.RequestResult
import dev.aggregate.data.util.TopHeadlinesRequest
import dev.aggregate.model.ui.UiArticle
import dev.aggregate.model.ui.toUiArticles
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface NewsPreferencesRepository {
    fun observeAll(request: TopHeadlinesRequest): Flow<List<UiArticle>>
    fun observeBookmarkedArticles(): Flow<List<UiArticle>>
}

class CompositeNewsPreferencesRepository @Inject constructor(
    val newsRepository: NewsRepository,
    val preferencesRepository: PreferencesRepository,
) : NewsPreferencesRepository {

    override fun observeAll(request: TopHeadlinesRequest): Flow<List<UiArticle>> =
        newsRepository.getTopHeadlines(request)
            .combine(preferencesRepository.userData) { result, userData ->
                result.data?.articles.toUiArticles(userData)
            }

    override fun observeBookmarkedArticles(): Flow<List<UiArticle>> {
        preferencesRepository.userData
            .map { it.bookmarkedArticleIds }
            .distinctUntilChanged()
            .flatMapLatest { bookmarkedArticleIds ->
                when {
                    bookmarkedArticleIds.isEmpty() -> emptyFlow<List<UiArticle>>()
                    else ->
                }
            }
        TODO("Not yet implemented")
    }
}
