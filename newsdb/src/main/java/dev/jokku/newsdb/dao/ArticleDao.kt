package dev.jokku.newsdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import dev.jokku.newsdb.model.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles")
    fun getAll(): Flow<List<ArticleEntity>>

    @Insert
    suspend fun insert(articles: List<ArticleEntity>)

    @Delete
    suspend fun remove(articles: List<ArticleEntity>)

    @Query("DELETE FROM articles")
    suspend fun clean()
}