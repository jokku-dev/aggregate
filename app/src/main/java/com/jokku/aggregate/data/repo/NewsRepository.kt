package com.jokku.aggregate.data.repo

import com.jokku.aggregate.R
import com.jokku.aggregate.data.local.LocalDataSource
import com.jokku.aggregate.data.remote.RemoteDataSource
import com.jokku.aggregate.data.remote.model.RemoteNewsResponse
import com.jokku.aggregate.presentation.model.UiErrorMessage
import com.jokku.aggregate.presentation.entity.TopHeadlinesRequest
import com.jokku.aggregate.domain.Result
import com.jokku.aggregate.presentation.model.UiText
import com.jokku.aggregate.util.WrongResponseConstants.UNKNOWN_ERROR
import io.ktor.client.plugins.ResponseException
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface NewsRepository {
    suspend fun getFavoriteCategoryArticles(request: TopHeadlinesRequest): Result<RemoteNewsResponse>
    fun observeRandomArticles(): Flow<Set<String>>
}

class MainNewsRepository @Inject constructor(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource,

    ) : NewsRepository {
    override suspend fun getFavoriteCategoryArticles(
        request: TopHeadlinesRequest
    ): Result<RemoteNewsResponse> {
        return try {
            val response = remote.getTopHeadlineArticles(request)
            if (response.isSuccess) {
                Result.Success(data = response)
            }
            else { // We have error type of response here, it means that the database can't have this data either
                Result.Failure(
                    message = UiErrorMessage(
                        text = if (response.errorMessage != null) {
                            UiText.DynamicString(response.errorMessage)
                        } else {
                            UiText.StringResource(R.string.no_articles_found)
                        }
                    ),
                    data = response
                )
            }
        } catch (e: ResponseException) { // 300, 400, 500
//            TODO("add cache call result to data")
            Result.Failure(
                message = UiErrorMessage(
                    text = UiText.DynamicString(e.response.status.description)
                ),
                data = RemoteNewsResponse(status = "")
            )
        } catch (e: Exception) {
//            TODO("add cache call result to data")
            Result.Failure(
                message = UiErrorMessage(
                    text = UiText.DynamicString(e.message ?: UNKNOWN_ERROR)
                ),
                data = RemoteNewsResponse(status = "")
            )
        }
    }

    override fun observeRandomArticles(): Flow<Set<String>> {
        TODO("Not yet implemented")
    }
}

enum class DataSourceType {
    REMOTE, CACHED, PREFERENCES
}