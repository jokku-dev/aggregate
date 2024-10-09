package dev.aggregate.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import dev.aggregate.database.entity.TopHeadlinesArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TopHeadlinesDao : BookmarksDao {
    @Query("SELECT * FROM TOP_HEADLINES_ARTICLES")
    suspend fun getTopHeadlines(): List<TopHeadlinesArticleEntity>

    @Query("SELECT * FROM TOP_HEADLINES_ARTICLES")
    fun observeTopHeadlines(): Flow<List<TopHeadlinesArticleEntity>>

    @Upsert
    suspend fun upsertTopHeadlinesArticles(topHeadlinesArticles: List<TopHeadlinesArticleEntity>)

    @Query("DELETE FROM TOP_HEADLINES_ARTICLES")
    suspend fun deleteTopHeadlinesArticles()
}
