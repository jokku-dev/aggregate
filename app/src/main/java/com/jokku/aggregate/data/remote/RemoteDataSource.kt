package com.jokku.aggregate.data.remote

import com.jokku.aggregate.data.remote.HttpRouts.API_KEY
import com.jokku.aggregate.data.remote.HttpRouts.CATEGORY
import com.jokku.aggregate.data.remote.HttpRouts.COUNTRY
import com.jokku.aggregate.data.remote.HttpRouts.LANGUAGE
import com.jokku.aggregate.data.remote.HttpRouts.PAGE
import com.jokku.aggregate.data.remote.HttpRouts.PAGE_SIZE
import com.jokku.aggregate.data.remote.HttpRouts.QUERY
import com.jokku.aggregate.data.remote.HttpRouts.SOURCES
import com.jokku.aggregate.data.remote.model.RemoteNewsResponse
import com.jokku.aggregate.presentation.entity.TopHeadlinesRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import javax.inject.Inject

interface RemoteDataSource {

    suspend fun getTopHeadlineArticles(request: TopHeadlinesRequest): RemoteNewsResponse
}

class NewsRemoteDataSource @Inject constructor(
    private val client: HttpClient
) : RemoteDataSource {

    override suspend fun getTopHeadlineArticles(request: TopHeadlinesRequest): RemoteNewsResponse {
        return try {
            client.get {
                url(HttpRouts.TOP_HEADLINES)
                parameter(API_KEY, request.apiKey)
                parameter(LANGUAGE, request.category.language)
                if (request.category.source == null) {
                    parameter(COUNTRY, request.category.country)
                    parameter(CATEGORY, request.category.category)
                } else {
                    parameter(SOURCES, request.category.source)
                }
                parameter(QUERY, request.q)
                parameter(PAGE_SIZE, request.pageSize)
                parameter(PAGE, request.page)
            }.body()
        } catch (e: Exception) {
            throw e
        }
    }
}