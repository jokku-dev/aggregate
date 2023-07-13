package com.jokku.aggregate.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.jokku.aggregate.data.local.database.entity.LocalBookmarkedArticle
import com.jokku.aggregate.data.local.database.entity.LocalFavoriteTopHeadlinesResponse
import com.jokku.aggregate.data.local.database.entity.intermediate.LocalFavoriteResponseWithArticles
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteNewsResponseDao {

    @Transaction
    @Query(value = """
        SELECT * FROM FAVORITE_TOP_HEADLINES_RESPONSES
        WHERE countryId = :country AND categoryId = :category LIMIT 1
            """)
    fun observeFavoriteTopHeadlines(
        country: String,
        category: String
    ): Flow<LocalFavoriteResponseWithArticles>

    @Transaction
    @Query(value = """
        SELECT * FROM FAVORITE_TOP_HEADLINES_RESPONSES
        WHERE sourceId = :source LIMIT 1
    """)
    fun observeFavoriteTopHeadlines(
        source: String
    ): Flow<LocalFavoriteResponseWithArticles>

    @Upsert
    suspend fun upsertFavoriteNewsResponse(newsResponse: LocalFavoriteTopHeadlinesResponse)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmarkedArticle(article: LocalBookmarkedArticle)

    @Delete
    suspend fun deleteBookmarkedArticle(article: LocalBookmarkedArticle)
}