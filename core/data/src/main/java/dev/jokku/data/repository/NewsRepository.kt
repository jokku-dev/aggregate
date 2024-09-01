package dev.jokku.data.repository

import dev.jokku.aggregate.data.local.database.TopHeadlinesDao
import dev.jokku.data.CategoryCode
import dev.jokku.data.CountryCode
import dev.jokku.data.RequestResult
import dev.jokku.data.map
import dev.jokku.data.model.toArticleEntity
import dev.jokku.data.model.toArticlesResponseEntity
import dev.jokku.data.model.toArticlesResponse
import dev.jokku.data.util.MergeStrategy
import dev.jokku.data.util.RequestResponseMergeStrategy
import dev.jokku.data.toRequestResult
import dev.jokku.database.database.entity.intermediate.ResponseWithArticles
import dev.jokku.database.database.entity.intermediate.toArticlesResponse
import dev.jokku.model.ArticlesResponse
import dev.jokku.network.NewsApi
import dev.jokku.network.model.NetworkArticle
import dev.jokku.network.model.NetworkArticlesResponse
import dev.jokku.ui.old.ByCountryAndCategoryRequest
import dev.jokku.ui.old.BySourceRequest
import dev.jokku.ui.old.TopHeadlinesRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

interface NewsRepository {
    fun getLocalTopHeadlines(country: CountryCode): Flow<ArticlesResponse>
    fun getFavoriteTopHeadlines(
        countries: Set<CountryCode>,
        categories: Set<CategoryCode>
    ): Flow<List<ArticlesResponse>>

    fun observeRandomArticles(): Flow<Set<String>>

    fun getTopHeadlines(
        searchQuery: String,
        mergeStrategy: MergeStrategy<RequestResult<ArticlesResponse>> = RequestResponseMergeStrategy(),
    ): Flow<RequestResult<ArticlesResponse>>
}

class DefaultNewsRepository @Inject constructor(
    private val network: NewsApi,
    private val topHeadlinesDao: TopHeadlinesDao,
) : NewsRepository {


    override fun getLocalTopHeadlines(country: CountryCode): Flow<ArticlesResponse> {
        TODO()
    }

    override fun getFavoriteTopHeadlines(
        countries: Set<CountryCode>,
        categories: Set<CategoryCode>
    ): Flow<List<ArticlesResponse>> {
        TODO("Not yet implemented")
    }

    override fun observeRandomArticles(): Flow<Set<String>> {
        TODO("Not yet implemented")
    }

    override fun getTopHeadlines(
        searchQuery: String,
        mergeStrategy: MergeStrategy<RequestResult<ArticlesResponse>> = RequestResponseMergeStrategy()
    ): Flow<RequestResult<ArticlesResponse>> {

        val cachedResponse: Flow<RequestResult<ArticlesResponse>> =
            getTopHeadlinesFromDatabase()
                .map { result ->
                    result.map { response ->
                        response.toArticlesResponse()
                    }
                }

        val remoteArticles: Flow<RequestResult<ArticlesResponse>> =
            getTopHeadlinesFromServer(searchQuery)
                .map { result: RequestResult<NetworkArticlesResponse<NetworkArticle>> ->
                    result.map { response ->
                        response.toArticlesResponse()
                    }
                }
        return cachedResponse.combine(remoteArticles) { entities, netObj ->
            mergeStrategy.merge(entities, netObj)
        }
    }

    private fun getTopHeadlinesFromServer(
        request: TopHeadlinesRequest,
    ): Flow<RequestResult<NetworkArticlesResponse<NetworkArticle>>> {
        val netRequest = flow {
            when (request) {
                is ByCountryAndCategoryRequest -> emit(
                    network.topHeadlinesByCountryAndCategory(
                        country = request.country,
                        category = request.category,
                        query = request.query,
                        pageSize = request.pageSize,
                        page = request.page
                    )
                )

                is BySourceRequest -> emit(
                    network.topHeadlinesBySource(
                        sources = request.sources,
                        query = request.query,
                        pageSize = request.pageSize,
                        page = request.page
                    )
                )
            }
        }
            .onEach { result ->
                if (result.isSuccess) {
                    saveNetResponseToCache(checkNotNull(result.getOrThrow()))
                }
            }
            .map { it.toRequestResult() }
        val init =
            flowOf<RequestResult<NetworkArticlesResponse<NetworkArticle>>>(RequestResult.InProgress())
        return merge(init, netRequest)
    }

    private suspend fun saveNetResponseToCache(
        data: NetworkArticlesResponse<NetworkArticle>,
    ) {
        val responseEntity = data.toArticlesResponseEntity()
        val articleEntities = data.articles.map { netArticle -> netArticle.toArticleEntity() }
        topHeadlinesDao.upsertTopHeadlinesResponseWithArticles(responseEntity, articleEntities)
    }

    private fun getTopHeadlinesFromDatabase(): Flow<RequestResult<ResponseWithArticles>> {
        val dbRequest = topHeadlinesDao
            .observeFavoriteTopHeadlines()
            .map { RequestResult.Success(it) }
        val init = flowOf<RequestResult<ResponseWithArticles>>(RequestResult.InProgress())
        return merge(init, dbRequest)
    }

//    override fun getFavoriteTopHeadlines(
//        countries: Set<CountryCode>,
//        categories: Set<CategoryCode>
//    ): Flow<List<NewsResponse>> {
//        return try {
//            val response = remote.getTopHeadlineArticles(request)
//            if (response.isSuccess) {
//                Result.Success(actualData = response)
//            } else { // We have error type of response here, it means that the database can't have this data either
//                Result.Failure(
//                    message = UiErrorMessage(
//                        text = if (response.errorMessage != null) {
//                            UiText.DynamicString(response.errorMessage)
//                        } else {
//                            UiText.StringResource(R.string.no_articles_found)
//                        }
//                    ),
//                    cachedData = response
//                )
//            }
//        } catch (e: ResponseException) { // 300, 400, 500
////            TODO("add cache call result to data")
//            Result.Failure(
//                message = UiErrorMessage(
//                    text = UiText.DynamicString(e.response.status.description)
//                ),
//                cachedData = RemoteNewsResponse(status = "")
//            )
//        } catch (e: Exception) {
////            TODO("add cache call result to data")
//            Result.Failure(
//                message = UiErrorMessage(
//                    text = UiText.DynamicString(e.message ?: UNKNOWN_ERROR)
//                ),
//                cachedData = RemoteNewsResponse(status = "")
//            )
//        }
//    }
}