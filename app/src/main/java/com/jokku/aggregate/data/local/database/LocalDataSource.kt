package com.jokku.aggregate.data.local.database

import com.jokku.aggregate.data.local.database.entity.LocalNewsResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface LocalDataSource {
    fun observeNewsResponse(category: String): Flow<LocalNewsResponse>
    suspend fun upsertNewsResponse(newsResponse: LocalNewsResponse)
    suspend fun updateArticleStatus(bookmarked: Boolean, url: String)
}

class NewsLocalDataSource @Inject constructor(
    private val dao: NewsDao
) : LocalDataSource {
    override fun observeNewsResponse(category: String): Flow<LocalNewsResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun upsertNewsResponse(newsResponse: LocalNewsResponse) {
        TODO("Not yet implemented")
    }

    override suspend fun updateArticleStatus(bookmarked: Boolean, url: String) {
        TODO("Not yet implemented")
    }
}