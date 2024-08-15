package dev.jokku.newsdata

import dev.jokku.newsapi.NewsApi
import dev.jokku.newsapi.model.NetworkArticle
import dev.jokku.newsapi.model.NetworkResponse
import dev.jokku.newsdata.model.Article
import dev.jokku.newsdb.NewsDatabase
import dev.jokku.newsdb.model.ArticleEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class ArticlesRepository(
    private val database: NewsDatabase,
    private val api: NewsApi
) {

    fun getAll(): Flow<RequestResult<List<Article>>> {

        val cachedArticles: Flow<RequestResult.Success<List<ArticleEntity>>> = getAllFromDatabase()

        val remoteArticles: Flow<RequestResult<*>> = getAllFromServer()

    }

    private fun getAllFromServer(): Flow<RequestResult<*>> {
        return flow {
            emit(RequestResult.InProgress())
            emit(api.everything())
        }
            .map { result: Result<NetworkResponse<NetworkArticle>> -> result.toRequestResult() }
            .onEach { requestResult: RequestResult<NetworkResponse<NetworkArticle>> ->
                if (requestResult is RequestResult.Success) {
                    saveNetworkResponseToCache(checkNotNull(requestResult.data).articles)
                }
            }
    }

    private suspend fun saveNetworkResponseToCache(data: List<NetworkArticle>) {
        val entities = data.map { networkArticle -> networkArticle.toArticleEntity() }
        database.articlesDao.insert(entities)
    }

    private fun getAllFromDatabase(): Flow<RequestResult.Success<List<ArticleEntity>>> {
        return database.articlesDao
            .getAll()
            .map { RequestResult.Success(it) }
    }
}

/**
 * Class with state of retrieving data success
 */
sealed class RequestResult<E>(internal val data: E? = null) {
    class InProgress<E>(data: E? = null) : RequestResult<E>(data)
    class Success<E>(data: E) : RequestResult<E>(data)
    class Error<E> : RequestResult<E>()
}

/**
 * Requests result's data
 */
internal fun <T : Any> RequestResult<T>.requireData(): T = checkNotNull(data)

/**
 * Maps request result's data into what is passed in mapper parameter
 */
internal fun <I, O> RequestResult<I>.map(mapper: (I?) -> O): RequestResult<O> {
    val outData = mapper(data)
    return when (this) {
        is RequestResult.InProgress -> RequestResult.InProgress(outData)
        is RequestResult.Success -> RequestResult.Success(outData)
        is RequestResult.Error -> RequestResult.Error()
    }
}

/**
 * Transforms Kotlin Result class to custom RequestResult
 */
internal fun <T> Result<T>.toRequestResult(): RequestResult<T> {
    return when {
        isSuccess -> RequestResult.Success(getOrThrow())
        isFailure -> RequestResult.Error()
        else -> error("impossible branch")
    }
}