package dev.jokku.database.di

import android.content.Context
import androidx.room.Room
import dev.jokku.aggregate.data.local.database.TopHeadlinesDao
import dev.jokku.aggregate.data.local.database.NewsRoomDatabase
import dagger.hilt.components.SingletonComponent
import kotlin.jvm.java

@dagger.Module
@dagger.hilt.InstallIn(dagger.hilt.components.SingletonComponent::class)
object DatabaseModule {

    @dagger.Provides
    fun provideNewsDao(database: NewsRoomDatabase): TopHeadlinesDao = database.topHeadlinesDao()

    @javax.inject.Singleton
    @dagger.Provides
    fun provideDatabase(@dagger.hilt.android.qualifiers.ApplicationContext context: Context): NewsRoomDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            NewsRoomDatabase::class.java,
            "News.db"
        ).build()
    }
}