package dev.jokku.aggregate.di

import android.content.Context
import androidx.room.Room
import dev.jokku.aggregate.data.local.database.NewsDao
import dev.jokku.aggregate.data.local.database.NewsDatabase
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
    fun provideNewsDao(database: NewsDatabase): NewsDao = database.newsDao()

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