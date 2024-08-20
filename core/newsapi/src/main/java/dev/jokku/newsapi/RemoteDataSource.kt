package dev.jokku.newsapi

import dev.jokku.newsapi.HttpRouts.API_KEY
import dev.jokku.newsapi.HttpRouts.CATEGORY
import dev.jokku.newsapi.HttpRouts.COUNTRY
import dev.jokku.newsapi.HttpRouts.PAGE
import dev.jokku.newsapi.HttpRouts.PAGE_SIZE
import dev.jokku.newsapi.HttpRouts.QUERY
import dev.jokku.newsapi.HttpRouts.SOURCES
import dev.jokku.newsapi.model.NetworkTopHeadlines
import dev.jokku.newsapi.model.NetworkSources
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import kotlin.collections.get

interface RemoteDataSource {

    suspend fun getTopHeadlineArticles(request: dev.jokku.aggregate.domain.TopHeadlinesRequest): NetworkTopHeadlines
    suspend fun getTopHeadlineSources(request: dev.jokku.aggregate.domain.SourcesRequest): NetworkSources
}

class NewsRemoteDataSource @javax.inject.Inject constructor(
    private val client: HttpClient
) : RemoteDataSource {

    override suspend fun getTopHeadlineArticles(request: dev.jokku.aggregate.domain.TopHeadlinesRequest): NetworkTopHeadlines {
        return try {
            when (request) {
                is dev.jokku.aggregate.domain.ByCountryAndCategoryRequest -> {
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

                is dev.jokku.aggregate.domain.BySourceRequest -> {
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

    override suspend fun getTopHeadlineSources(request: dev.jokku.aggregate.domain.SourcesRequest): NetworkSources {
        TODO("Not yet implemented")
    }
}