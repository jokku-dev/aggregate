package com.jokku.aggregate.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jokku.aggregate.data.local.database.entity.ArticleEntity
import com.jokku.aggregate.data.local.database.entity.ArticleEntitySource
import com.jokku.aggregate.data.local.database.entity.BookmarkedArticleEntity
import com.jokku.aggregate.data.local.database.entity.SourceEntity
import com.jokku.aggregate.data.local.database.entity.SourcesResponseEntity
import com.jokku.aggregate.data.local.database.entity.ArticlesResponseEntity

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