package com.jokku.aggregate.data.remote

import com.jokku.aggregate.data.remote.HttpRouts.API_KEY
import com.jokku.aggregate.data.remote.HttpRouts.CATEGORY
import com.jokku.aggregate.data.remote.HttpRouts.COUNTRY
import com.jokku.aggregate.data.remote.HttpRouts.PAGE
import com.jokku.aggregate.data.remote.HttpRouts.PAGE_SIZE
import com.jokku.aggregate.data.remote.HttpRouts.QUERY
import com.jokku.aggregate.data.remote.HttpRouts.SOURCES
import com.jokku.aggregate.data.remote.model.RemoteNewsResponse
import com.jokku.aggregate.data.remote.model.RemoteSourcesResponse
import com.jokku.aggregate.domain.SourcesRequest
import com.jokku.aggregate.domain.TopHeadlinesRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import javax.inject.Inject

interface RemoteDataSource {

    suspend fun getTopHeadlineArticles(request: TopHeadlinesRequest): RemoteNewsResponse
    suspend fun getTopHeadlineSources(request: SourcesRequest): RemoteSourcesResponse
}

class NewsRemoteDataSource @Inject constructor(
    private val client: HttpClient
) : RemoteDataSource {

    override suspend fun getTopHeadlineArticles(request: TopHeadlinesRequest): RemoteNewsResponse {
        return try {
            when (request) {
                is TopHeadlinesRequest.ByCountryAndCategoryRequest -> {
                    client.get {
                        url(HttpRouts.TOP_HEADLINES)
                        parameter(API_KEY, request.apiKey)
//                        parameter(LANGUAGE, request.language)
                        parameter(COUNTRY, request.country)
                        parameter(CATEGORY, request.category)
                        parameter(QUERY, request.query)
                        parameter(PAGE_SIZE, request.pageSize)
                        parameter(PAGE, request.page)
                    }.body()
                }

                is TopHeadlinesRequest.BySourceRequest -> {
                    client.get {
                        url(HttpRouts.TOP_HEADLINES)
                        parameter(API_KEY, request.apiKey)
//                        parameter(LANGUAGE, request.language)
                        parameter(SOURCES, request.source)
                        parameter(QUERY, request.query)
                        parameter(PAGE_SIZE, request.pageSize)
                        parameter(PAGE, request.page)
                    }.body()

                }
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getTopHeadlineSources(request: SourcesRequest): RemoteSourcesResponse {
        TODO("Not yet implemented")
    }
}