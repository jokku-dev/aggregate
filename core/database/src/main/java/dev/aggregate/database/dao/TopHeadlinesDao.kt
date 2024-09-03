package dev.aggregate.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import dev.aggregate.database.entity.ArticleEntity
import dev.aggregate.database.entity.ArticlesResponseEntity
import dev.aggregate.database.database.entity.intermediate.ResponseWithArticles
import kotlinx.coroutines.flow.Flow

@Dao
interface TopHeadlinesDao : BookmarksDao {
    // We need @Transaction when using transitive class as a response because it's a complex query
    @Transaction
    @Query("SELECT * FROM ARTICLE_RESPONSES")
    fun observeFavoriteTopHeadlines(): Flow<ResponseWithArticles>


    @Upsert
    suspend fun upsertTopHeadlinesResponse(topHeadlinesResponse: ArticlesResponseEntity): Long

    @Upsert
    suspend fun upsertTopHeadlinesArticles(topHeadlinesArticles: List<ArticleEntity>): List<Long>

    @Upsert
    suspend fun upsertTopHeadlinesResponseWithArticles(
        topHeadlinesResponse: ArticlesResponseEntity,
        topHeadlinesArticles: List<ArticleEntity>
    ) : Long

    @Delete(entity = ArticlesResponseEntity::class)
    suspend fun deleteTopHeadlinesResponse(topHeadlinesResponse: ArticlesResponseEntity)

    @Transaction
    suspend fun upsertTopHeadlinesResponseAndArticles(
        topHeadlinesResponse: ArticlesResponseEntity,
        topHeadlinesArticles: List<ArticleEntity>
    ) {
        upsertTopHeadlinesResponse(topHeadlinesResponse)
        upsertTopHeadlinesArticles(topHeadlinesArticles)
    }
}