package com.jokku.aggregate.data.repo

import com.jokku.aggregate.R
import com.jokku.aggregate.data.CategoryCode
import com.jokku.aggregate.data.CountryCode
import com.jokku.aggregate.data.local.database.NewsDao
import com.jokku.aggregate.data.local.database.entity.LocalNewsResponse
import com.jokku.aggregate.data.remote.RemoteDataSource
import com.jokku.aggregate.data.remote.model.RemoteNewsResponse
import com.jokku.aggregate.data.repo.model.NewsResponse
import com.jokku.aggregate.presentation.model.UiErrorMessage
import com.jokku.aggregate.domain.Result
import com.jokku.aggregate.domain.TopHeadlinesRequest
import com.jokku.aggregate.presentation.model.UiText
import io.ktor.client.plugins.ResponseException
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface NewsRepository {
    fun getTopHeadlines(): Flow<List<LocalNewsResponse>>
    fun getFavoriteTopHeadlines(
        countries: Set<CountryCode>,
        categories: Set<CategoryCode>
    ): Flow<List<LocalNewsResponse>>

    fun observeRandomArticles(): Flow<Set<String>>
}

class DefaultNewsRepository @Inject constructor(
    private val remote: RemoteDataSource,
    private val local: NewsDao
) : NewsRepository {

    private var latestNews: List<NewsResponse> = emptyList()

    override fun getTopHeadlines(): Flow<List<LocalNewsResponse>> {
        local.observeTopHeadlines()
    }

    override fun getFavoriteTopHeadlines(
        countries: Set<CountryCode>,
        categories: Set<CategoryCode>
    ): Flow<List<LocalNewsResponse>> {
        return try {
            val response = remote.getTopHeadlineArticles(request)
            if (response.isSuccess) {
                Result.Success(actualData = response)
            } else { // We have error type of response here, it means that the database can't have this data either
                Result.Failure(
                    message = UiErrorMessage(
                        text = if (response.errorMessage != null) {
                            UiText.DynamicString(response.errorMessage)
                        } else {
                            UiText.StringResource(R.string.no_articles_found)
                        }
                    ),
                    cachedData = response
                )
            }
        } catch (e: ResponseException) { // 300, 400, 500
//            TODO("add cache call result to data")
            Result.Failure(
                message = UiErrorMessage(
                    text = UiText.DynamicString(e.response.status.description)
                ),
                cachedData = RemoteNewsResponse(status = "")
            )
        } catch (e: Exception) {
//            TODO("add cache call result to data")
            Result.Failure(
                message = UiErrorMessage(
                    text = UiText.DynamicString(e.message ?: UNKNOWN_ERROR)
                ),
                cachedData = RemoteNewsResponse(status = "")
            )
        }
    }

    override fun observeRandomArticles(): Flow<Set<String>> {
        TODO("Not yet implemented")
    }
}