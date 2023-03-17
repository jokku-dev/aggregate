package com.jokku.aggregate.di

import android.content.Context
import com.jokku.aggregate.data.DataStoreRepository
import com.jokku.aggregate.data.WelcomeDataStoreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MainModule {

    @Binds
    @Singleton
    abstract fun provideDataStoreRepository(impl: WelcomeDataStoreRepository): DataStoreRepository

    @Binds
    @Singleton
    abstract fun provideApplicationContext(@ApplicationContext context: Context): Context
}