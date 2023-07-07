package com.jokku.aggregate.data.local.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.jokku.aggregate.data.local.database.entity.LocalFavoriteTopHeadlinesResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query(value = """SELECT * FROM news_response
            WHERE category = :category LIMIT 1""")
    fun observeTopHeadlines(country: String, category: String, query: String = ""): Flow<LocalFavoriteTopHeadlinesResponse>

    @Query(value = """""")
    fun observeTopHeadlines(source: String, query: String = ""): Flow<LocalFavoriteTopHeadlinesResponse>

    @Upsert
    suspend fun upsertNewsResponse(newsResponse: LocalFavoriteTopHeadlinesResponse)

    @Query("UPDATE article SET bookmarked = :bookmarked WHERE url = :url")
    suspend fun updateArticleStatus(bookmarked: Boolean, url: String)


}