package dev.jokku.aggregate.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.jokku.aggregate.data.local.database.entity.ArticleEntity
import dev.jokku.aggregate.data.local.database.entity.ArticleEntitySource
import dev.jokku.aggregate.data.local.database.entity.BookmarkedArticleEntity
import dev.jokku.aggregate.data.local.database.entity.ArticlesResponseEntity
import dev.jokku.database.dao.BookmarksDao
import dev.jokku.database.entity.SourceEntity
import dev.jokku.database.entity.SourcesResponseEntity
import dev.jokku.database.utils.Converters

// Wrapper class for library database to use it in public
class NewsDatabase internal constructor(private val database: NewsRoomDatabase) {

    val topHeadlinesDao: TopHeadlinesDao
        get() = database.topHeadlinesDao()

    val bookmarksDao: BookmarksDao
        get() = database.bookmarksDao()
}

@Database(
    entities = [
        ArticleEntity::class,
        ArticleEntitySource::class,
        ArticlesResponseEntity::class,
        BookmarkedArticleEntity::class,
        SourceEntity::class,
        SourcesResponseEntity::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
internal abstract class NewsRoomDatabase : RoomDatabase() {
    abstract fun topHeadlinesDao(): TopHeadlinesDao
    abstract fun bookmarksDao(): BookmarksDao

    fun NewsDatabase(applicationContext: Context): NewsDatabase {
        val newsRoomDatabase = Room.databaseBuilder(
            applicationContext,
            NewsRoomDatabase::class.java,
            "news"
        ).build()
        return NewsDatabase(newsRoomDatabase)
    }
}