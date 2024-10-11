package dev.aggregate.data.repository

import dev.aggregate.common.util.Logger
import dev.aggregate.data.RequestResult
import dev.aggregate.data.map
import dev.aggregate.data.toArticle
import dev.aggregate.data.toEntity
import dev.aggregate.data.toRequestResult
import dev.aggregate.data.util.ByCountryAndCategoryRequest
import dev.aggregate.data.util.BySourceRequest
import dev.aggregate.data.util.MergeStrategy
import dev.aggregate.data.util.RequestResponseMergeStrategy
import dev.aggregate.data.util.TopHeadlinesRequest
import dev.aggregate.database.NewsDatabase
import dev.aggregate.database.entity.TopHeadlinesArticleEntity
import dev.aggregate.model.Article
import dev.aggregate.network.NewsApi
import dev.aggregate.network.model.NetworkArticle
import dev.aggregate.network.model.NetworkArticlesResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

interface NewsRepository {
    fun observeBookmarkedArticles(articleIds: Set<String>): Flow<RequestResult<List<Article>>>

    fun getTopHeadlines(
        request: TopHeadlinesRequest,
        mergeStrategy: MergeStrategy<RequestResult<List<Article>>> = RequestResponseMergeStrategy(),
    ): Flow<RequestResult<List<Article>>>
}

class DefaultNewsRepository @Inject constructor(
    private val network: NewsApi,
    private val database: NewsDatabase,
    private val logger: Logger
) : NewsRepository {
    override fun observeBookmarkedArticles(
        articleIds: Set<String>
    ): Flow<RequestResult<List<Article>>> {
        TODO("Not yet implemented")
    }

    override fun getTopHeadlines(
        request: TopHeadlinesRequest,
        mergeStrategy: MergeStrategy<RequestResult<List<Article>>>
    ): Flow<RequestResult<List<Article>>> {
        val cachedResponse: Flow<RequestResult<List<Article>>> = getTopHeadlinesFromDatabase()
        val remoteArticles: Flow<RequestResult<List<Article>>> = getTopHeadlinesFromServer(request)
        return cachedResponse.combine(remoteArticles, mergeStrategy::merge)
            .flatMapLatest { result ->
                if (result is RequestResult.Success) {
                    database.topHeadlinesDao.observeTopHeadlines()
                        .map { entities -> entities.map { it.toArticle() } }
                        .map { RequestResult.Success(it) }
                } else {
                    flowOf(result)
                }
            }
    }

    private fun getTopHeadlinesFromServer(
        request: TopHeadlinesRequest,
    ): Flow<RequestResult<List<Article>>> {
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
                    saveNetResponseToCache(result.getOrThrow().articles)
                }
            }
            .onEach { result ->
                if (result.isFailure) {
                    logger.e(
                        LOG_TAG,
                        "Error getting data from server. Cause = ${result.exceptionOrNull()}"
                    )
                }
            }
            .map { it.toRequestResult() }
        val init =
            flowOf<RequestResult<NetworkArticlesResponse<NetworkArticle>>>(RequestResult.InProgress())
        return merge(init, netRequest).map { result ->
            result.map { response ->
                response.articles.map { article -> article.toArticle() }
            }
        }
    }

    private suspend fun saveNetResponseToCache(articles: List<NetworkArticle>) {
        val articleEntities = articles.map { netArticle -> netArticle.toEntity() }
        database.topHeadlinesDao.deleteTopHeadlinesArticles()
        database.topHeadlinesDao.upsertTopHeadlinesArticles(articleEntities)
    }

    private fun getTopHeadlinesFromDatabase(): Flow<RequestResult<List<Article>>> {
        val dbRequest = database.topHeadlinesDao::getTopHeadlines.asFlow()
            .map<List<TopHeadlinesArticleEntity>, RequestResult<List<TopHeadlinesArticleEntity>>> {
                RequestResult.Success(it)
            }
            .catch {
                logger.e(LOG_TAG, "Error getting from database. Cause = $it")
                emit(RequestResult.Error(error = it))
            }
        val init =
            flowOf<RequestResult<List<TopHeadlinesArticleEntity>>>(RequestResult.InProgress())
        return merge(init, dbRequest).map { result ->
            result.map { entities ->
                entities.map { entity -> entity.toArticle() }
            }
        }
    }

    private companion object {
        const val LOG_TAG = "ArticlesRepository"
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
//            TODO("add cache call result to data")
//            Result.Failure(
//                message = UiErrorMessage(
//                    text = UiText.DynamicString(e.response.status.description)
//                ),
//                cachedData = RemoteNewsResponse(status = "")
//            )
//        } catch (e: Exception) {
//            TODO("add cache call result to data")
//            Result.Failure(
//                message = UiErrorMessage(
//                    text = UiText.DynamicString(e.message ?: UNKNOWN_ERROR)
//                ),
//                cachedData = RemoteNewsResponse(status = "")
//            )
//        }
//    }
}
