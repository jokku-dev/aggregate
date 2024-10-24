package dev.aggregate.database.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.aggregate.database.NewsRoomDatabase
import dev.aggregate.database.dao.BookmarksDao
import dev.aggregate.database.dao.TopHeadlinesDao

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun provideTopHeadlinesDao(database: NewsRoomDatabase): TopHeadlinesDao = database.topHeadlinesDao()

    @Provides
    fun provideBookmarksDao(database: NewsRoomDatabase): BookmarksDao = database.bookmarksDao()
}
