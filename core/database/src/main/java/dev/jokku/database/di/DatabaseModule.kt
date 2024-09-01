package dev.jokku.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.jokku.aggregate.data.local.database.NewsDatabase
import dev.jokku.aggregate.data.local.database.TopHeadlinesDao
import dev.jokku.aggregate.data.local.database.NewsRoomDatabase
import javax.inject.Singleton
import kotlin.jvm.java

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