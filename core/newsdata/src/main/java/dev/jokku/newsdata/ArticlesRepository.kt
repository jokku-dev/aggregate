package dev.jokku.newsdata

import dev.jokku.newsapi.NewsApi
import dev.jokku.newsapi.model.NetArticle
import dev.jokku.newsapi.model.NetResponse
import dev.jokku.newsdata.model.Article
import dev.jokku.newsdata.model.MergeStrategy
import dev.jokku.newsdb.NewsDatabase
import dev.jokku.newsdb.model.ArticleEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach

class ArticlesRepository(
    private val database: NewsDatabase,
    private val api: NewsApi,
    private val mergeStrategy: MergeStrategy<List<Article>>,
) {

    fun getAll(): Flow<RequestResult<List<Article>>> {

        val cachedArticles: Flow<RequestResult<List<Article>>> = getAllFromDatabase()
            .map { result ->
                result.map { articleEntities ->
                    articleEntities.map { it.toArticle() }
                }
            }

        val remoteArticles: Flow<RequestResult<List<Article>>> = getAllFromServer()
            .map { result: RequestResult<NetResponse<NetArticle>> ->
                result.map { response ->
                    response.articles.map { it.toArticle() }
                }
            }
        return cachedArticles.combine(remoteArticles) { entities, netObj ->
            mergeStrategy.merge(entities, netObj)
        }
    }

    private fun getAllFromServer(): Flow<RequestResult<NetResponse<NetArticle>>> {
        val netRequest = flow { emit(api.everything()) }
            .onEach { result ->
                if (result.isSuccess) {
                    saveNetResponseToCache(checkNotNull(result.getOrThrow()).articles)
                }
            }
            .map { it.toRequestResult() }
        val init = flowOf<RequestResult<NetResponse<NetArticle>>>(RequestResult.InProgress())
        return merge(init, netRequest)
    }

    private suspend fun saveNetResponseToCache(data: List<NetArticle>) {
        val entities = data.map { netArticle -> netArticle.toArticleEntity() }
        database.articlesDao.insert(entities)
    }

    private fun getAllFromDatabase(): Flow<RequestResult<List<ArticleEntity>>> {
        val dbRequest = database.articlesDao
            .getAll()
            .map { RequestResult.Success(it) }
        val init = flowOf<RequestResult<List<ArticleEntity>>>(RequestResult.InProgress())
        return merge(init, dbRequest)
    }
}