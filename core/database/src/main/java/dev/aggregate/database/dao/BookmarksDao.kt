package dev.aggregate.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import dev.aggregate.database.entity.BookmarkedArticleEntity

@Dao
interface BookmarksDao {
    @Insert
    suspend fun insertBookmarkedArticle(article: BookmarkedArticleEntity): Long

    @Delete
    suspend fun deleteBookmarkedArticle(article: BookmarkedArticleEntity)
}
