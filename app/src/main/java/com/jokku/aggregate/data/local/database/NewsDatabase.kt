package com.jokku.aggregate.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jokku.aggregate.data.local.database.entity.LocalArticle
import com.jokku.aggregate.data.local.database.entity.LocalArticleSource
import com.jokku.aggregate.data.local.database.entity.LocalBookmarkedArticle
import com.jokku.aggregate.data.local.database.entity.LocalFavoriteTopHeadlinesResponse
import com.jokku.aggregate.data.local.database.entity.LocalSource
import com.jokku.aggregate.data.local.database.entity.LocalSourcesResponse
import com.jokku.aggregate.data.local.database.entity.LocalTopHeadlinesResponse

@Database(
    entities = [
        LocalArticle::class,
        LocalArticleSource::class,
        LocalBookmarkedArticle::class,
        LocalFavoriteTopHeadlinesResponse::class,
        LocalSource::class,
        LocalSourcesResponse::class,
        LocalTopHeadlinesResponse::class
    ],
    version = 1,
    exportSchema = false
)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): FavoriteNewsResponseDao
}