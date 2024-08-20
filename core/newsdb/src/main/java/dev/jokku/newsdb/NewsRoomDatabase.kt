package dev.jokku.newsdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.jokku.newsdb.dao.ArticlesDao
import dev.jokku.newsdb.model.ArticleEntity
import dev.jokku.newsdb.utils.Converters
import kotlin.jvm.java

class NewsDatabase internal constructor(private val database: NewsRoomDatabase) {

    val articlesDao: ArticlesDao
        get() = database.articleDao()
}

@Database(entities = [ArticleEntity::class], version = 1)
@TypeConverters(Converters::class)
internal abstract class NewsRoomDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticlesDao
}

fun NewsDatabase(applicationContext: Context): NewsDatabase {
    val newsRoomDatabase = Room.databaseBuilder(
        applicationContext,
        NewsRoomDatabase::class.java,
        "news"
    ).build()
    return NewsDatabase(newsRoomDatabase)
}