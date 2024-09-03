package dev.aggregate.database.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.aggregate.data.local.database.NewsDatabase
import dev.aggregate.data.local.database.NewsRoomDatabase
import dev.aggregate.database.dao.TopHeadlinesDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideNewsDao(database: NewsRoomDatabase): TopHeadlinesDao = database.topHeadlinesDao()

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): NewsDatabase {
        return NewsDatabase(context)
    }
}