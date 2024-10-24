package dev.aggregate.topheadlines

import dev.aggregate.data.RequestResult
import dev.aggregate.data.map
import dev.aggregate.data.repository.NewsRepository
import dev.aggregate.data.repository.PreferencesRepository
import dev.aggregate.data.util.ByCountryAndCategoryRequest
import dev.aggregate.data.util.BySourceRequest
import dev.aggregate.data.util.findNetworkConstantBySerialName
import dev.aggregate.model.network.Category
import dev.aggregate.model.network.Country
import dev.aggregate.model.ui.UiArticle
import dev.aggregate.model.ui.toUiArticles
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTopHeadlineArticlesUseCase @Inject constructor(
    private val newsRepository: NewsRepository,
    private val preferencesRepository: PreferencesRepository
) {

    operator fun invoke(
        searchQuery: String?,
        category: String?
    ): Flow<RequestResult<List<UiArticle>>> {
        return preferencesRepository.userData.flatMapLatest { userData ->
            val request = if (userData.favoriteCountry != null || userData.favoriteCategory != null) {
                val priorityCategory = if (category == userData.favoriteCategory) {
                    userData.favoriteCategory
                } else {
                    category
                }
                ByCountryAndCategoryRequest(
                    country = findNetworkConstantBySerialName<Country>(userData.favoriteCountry),
                    category = findNetworkConstantBySerialName<Category>(priorityCategory),
                    query = searchQuery
                )
            } else {
                BySourceRequest(
                    sources = userData.sources,
                    query = searchQuery
                )
            }

            newsRepository.getTopHeadlines(request).map { result ->
                result.map { articles ->
                    articles.toUiArticles(userData.bookmarkedArticleIds, userData.viewedArticleIds)
                }
            }
        }
    }
}
