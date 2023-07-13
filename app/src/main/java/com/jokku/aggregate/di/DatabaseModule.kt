package com.jokku.aggregate.di

import android.content.Context
import androidx.room.Room
import com.jokku.aggregate.data.local.database.FavoriteNewsResponseDao
import com.jokku.aggregate.data.local.database.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideNewsDao(database: NewsDatabase): FavoriteNewsResponseDao = database.newsDao()

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): NewsDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            NewsDatabase::class.java,
            "News.db"
        ).build()
    }
}