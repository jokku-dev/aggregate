package dev.aggregate.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.aggregate.database.dao.BookmarksDao
import dev.aggregate.database.dao.TopHeadlinesDao
import dev.aggregate.database.entity.ArticleEntity
import dev.aggregate.database.entity.ArticleSourceEntity
import dev.aggregate.database.entity.ArticlesResponseEntity
import dev.aggregate.database.entity.BookmarkedArticleEntity
import dev.aggregate.database.entity.SourceEntity
import dev.aggregate.database.entity.SourcesResponseEntity
import dev.aggregate.database.utils.Converters

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
        ArticleEntity::class,
        ArticleSourceEntity::class,
        ArticlesResponseEntity::class,
        BookmarkedArticleEntity::class,
        SourceEntity::class,
        SourcesResponseEntity::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class NewsRoomDatabase : RoomDatabase() {
    abstract fun topHeadlinesDao(): TopHeadlinesDao
    abstract fun bookmarksDao(): BookmarksDao
}

fun NewsDatabase(applicationContext: Context): NewsDatabase {
    val newsRoomDatabase = Room.databaseBuilder(
        applicationContext,
        NewsRoomDatabase::class.java,
        "news"
    ).build()
    return NewsDatabase(newsRoomDatabase)
}
