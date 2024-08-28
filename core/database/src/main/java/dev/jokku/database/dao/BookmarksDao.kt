package dev.jokku.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Upsert
import dev.jokku.aggregate.data.local.database.entity.BookmarkedArticleEntity

@Dao
interface BookmarksDao {

    @Upsert
    suspend fun upsertBookmarkedArticle(article: BookmarkedArticleEntity): Long

    @Delete
    suspend fun deleteBookmarkedArticle(article: BookmarkedArticleEntity)
}