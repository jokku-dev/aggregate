package dev.aggregate.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.aggregate.database.dao.BookmarksDao
import dev.aggregate.database.dao.TopHeadlinesDao
import dev.aggregate.database.entity.BookmarkedArticleEntity
import dev.aggregate.database.entity.SourceEntity
import dev.aggregate.database.entity.TopHeadlinesArticleEntity

// Wrapper class for library database to use it in public
class NewsDatabase internal constructor(
    private val database: NewsRoomDatabase
) {
    val topHeadlinesDao: TopHeadlinesDao
        get() = database.topHeadlinesDao()

    val bookmarksDao: BookmarksDao
        get() = database.bookmarksDao()
}

@Database(
    entities = [
        TopHeadlinesArticleEntity::class,
        BookmarkedArticleEntity::class,
        SourceEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class NewsRoomDatabase : RoomDatabase() {
    abstract fun topHeadlinesDao(): TopHeadlinesDao
    abstract fun bookmarksDao(): BookmarksDao
}
