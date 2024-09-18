package dev.aggregate.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.aggregate.database.NewsDatabase
import dev.aggregate.database.NewsRoomDatabase
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
        val newsRoomDatabase = Room.databaseBuilder(
            context,
            NewsRoomDatabase::class.java,
            "news"
        ).build()
        return NewsDatabase(newsRoomDatabase)
    }
}
