package com.jokku.aggregate.data.remote

import com.jokku.aggregate.data.remote.HttpRouts.API_KEY
import com.jokku.aggregate.data.remote.HttpRouts.CATEGORY
import com.jokku.aggregate.data.remote.HttpRouts.COUNTRY
import com.jokku.aggregate.data.remote.HttpRouts.PAGE
import com.jokku.aggregate.data.remote.HttpRouts.PAGE_SIZE
import com.jokku.aggregate.data.remote.HttpRouts.QUERY
import com.jokku.aggregate.data.remote.HttpRouts.SOURCES
import com.jokku.aggregate.data.remote.model.NetworkTopHeadlines
import com.jokku.aggregate.data.remote.model.NetworkSources
import com.jokku.aggregate.domain.ByCountryAndCategoryRequest
import com.jokku.aggregate.domain.BySourceRequest
import com.jokku.aggregate.domain.SourcesRequest
import com.jokku.aggregate.domain.TopHeadlinesRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import javax.inject.Inject

interface RemoteDataSource {

    suspend fun getTopHeadlineArticles(request: TopHeadlinesRequest): NetworkTopHeadlines
    suspend fun getTopHeadlineSources(request: SourcesRequest): NetworkSources
}

class NewsRemoteDataSource @Inject constructor(
    private val client: HttpClient
) : RemoteDataSource {

    override suspend fun getTopHeadlineArticles(request: TopHeadlinesRequest): NetworkTopHeadlines {
        return try {
            when (request) {
                is ByCountryAndCategoryRequest -> {
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

                is BySourceRequest -> {
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

    override suspend fun getTopHeadlineSources(request: SourcesRequest): NetworkSources {
        TODO("Not yet implemented")
    }
}