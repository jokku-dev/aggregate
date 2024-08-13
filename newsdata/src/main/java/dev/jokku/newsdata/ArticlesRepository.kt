package dev.jokku.newsdata

import dev.jokku.newsapi.NewsApi
import dev.jokku.newsdata.model.Article
import dev.jokku.newsdb.NewsDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ArticlesRepository(
    private val database: NewsDatabase,
    private val api: NewsApi
) {

    fun getAll(): RequestResult<Flow<List<Article>>> {
        return RequestResult.InProgress(
            database.articleDao
            .getAll()
            .map { articles -> articles.map { it.toArticle() } }
        )
    }
}

sealed class RequestResult<E>(protected val data: E?) {
    class InProgress<E>(data: E?) : RequestResult<E>(data)
    class Success<E>(data: E?) : RequestResult<E>(data)
    class Error<E>(data: E?) : RequestResult<E>(data)
}