package dev.aggregate.topheadlines

import dev.aggregate.data.RequestResult
import dev.aggregate.data.map
import dev.aggregate.data.repository.NewsRepository
import dev.aggregate.data.util.ByCountryAndCategoryRequest
import dev.aggregate.data.util.BySourceRequest
import dev.aggregate.model.UserData
import dev.aggregate.model.ui.UiArticle
import dev.aggregate.model.ui.toUiArticles
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTopHeadlineArticlesUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(searchQuery: String, userData: UserData): Flow<RequestResult<List<UiArticle>>> {
        val request = if (userData.countryCode != null || userData.categoryCode != null) {
            ByCountryAndCategoryRequest(
                country = userData.countryCode,
                category = userData.categoryCode,
                query = searchQuery
            )
        } else {
            BySourceRequest(
                sources = userData.sources,
                query = searchQuery
            )
        }

        return repository.getTopHeadlines(request).map { result ->
            result.map { articles ->
                articles.toUiArticles(userData.bookmarkedArticleIds, userData.viewedArticleIds)
            }
        }
    }
}
