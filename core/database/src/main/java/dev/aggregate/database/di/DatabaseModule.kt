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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): NewsRoomDatabase {
        return Room.databaseBuilder(
            context,
            NewsRoomDatabase::class.java,
            "news"
        ).build()
    }

    @Provides
    fun provideDatabase(roomDatabase: NewsRoomDatabase): NewsDatabase = NewsDatabase(roomDatabase)
}
