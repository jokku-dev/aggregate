package com.jokku.aggregate.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jokku.aggregate.data.local.entity.LocalArticle
import com.jokku.aggregate.data.local.entity.LocalArticleSource
import com.jokku.aggregate.data.local.entity.LocalNewsResponse
import com.jokku.aggregate.data.local.entity.LocalSource
import com.jokku.aggregate.data.local.entity.LocalSourcesResponse

@Database(
    entities = [
        LocalArticle::class,
        LocalArticleSource::class,
        LocalNewsResponse::class,
        LocalSource::class,
        LocalSourcesResponse::class
    ],
    version = 1,
    exportSchema = false
)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}