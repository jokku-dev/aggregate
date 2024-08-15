package dev.jokku.aggregate.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.jokku.aggregate.data.local.database.entity.ArticleEntity
import dev.jokku.aggregate.data.local.database.entity.ArticleEntitySource
import dev.jokku.aggregate.data.local.database.entity.BookmarkedArticleEntity
import dev.jokku.aggregate.data.local.database.entity.SourceEntity
import dev.jokku.aggregate.data.local.database.entity.SourcesResponseEntity
import dev.jokku.aggregate.data.local.database.entity.ArticlesResponseEntity

@Database(
    entities = [
        ArticleEntity::class,
        ArticleEntitySource::class,
        BookmarkedArticleEntity::class,
        SourceEntity::class,
        SourcesResponseEntity::class,
        ArticlesResponseEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}