package dev.aggregate.data.repository

import dev.aggregate.data.RequestResult
import dev.aggregate.data.map
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
    fun observeAll(articleIds: Set<String>): Flow<RequestResult<List<UiArticle>>>
    fun observeBookmarkedArticles(): Flow<RequestResult<List<UiArticle>>>
}

class CompositeNewsPreferencesRepository @Inject constructor(
    private val newsRepository: NewsRepository,
    private val preferencesRepository: PreferencesRepository,
) : NewsPreferencesRepository {

    override fun observeAll(articleIds: Set<String>): Flow<RequestResult<List<UiArticle>>> =
        newsRepository.observeBookmarkedArticles(articleIds)
            .combine(preferencesRepository.userData) { articles, userData ->
                articles.map { article ->
                    article.toUiArticles(userData.bookmarkedArticleIds, userData.viewedArticleIds)
                }
            }

    override fun observeBookmarkedArticles(): Flow<RequestResult<List<UiArticle>>> =
        preferencesRepository.userData
            .map { it.bookmarkedArticleIds }
            .distinctUntilChanged()
            .flatMapLatest { bookmarkedArticleIds ->
                when {
                    bookmarkedArticleIds.isEmpty() -> emptyFlow()
                    else -> observeAll(bookmarkedArticleIds)
                }
            }
}
