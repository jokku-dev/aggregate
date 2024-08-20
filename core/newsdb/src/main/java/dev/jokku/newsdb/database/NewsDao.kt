package dev.jokku.aggregate.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import dev.jokku.aggregate.data.local.database.entity.ArticleEntity
import dev.jokku.aggregate.data.local.database.entity.BookmarkedArticleEntity
import dev.jokku.aggregate.data.local.database.entity.ArticlesResponseEntity
import dev.jokku.aggregate.data.local.database.entity.intermediate.ResponseWithArticles
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    // We need @Transaction when using transitive class as a response because it's a complex query
    @Transaction
    @Query("""
        SELECT * FROM ARTICLE_RESPONSES
        WHERE country_code = :country AND category = :category LIMIT 1
            """)
    fun observeFavoriteTopHeadlines(
        country: String,
        category: String
    ): Flow<ResponseWithArticles>

    @Transaction
    @Query("""
        SELECT * FROM ARTICLE_RESPONSES
        WHERE source_id = :source LIMIT 1
    """)
    fun observeFavoriteTopHeadlines(
        source: String
    ): Flow<ResponseWithArticles>


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


    @Upsert
    suspend fun upsertBookmarkedArticle(article: BookmarkedArticleEntity): Long

    @Delete
    suspend fun deleteBookmarkedArticle(article: BookmarkedArticleEntity)
}