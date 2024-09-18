package dev.aggregate.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.aggregate.data.repository.DefaultNewsRepository
import dev.aggregate.data.repository.NewsRepository
import dev.aggregate.datastore.NewsPreferencesDataSource
import dev.aggregate.datastore.PreferencesDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDataStoreRepository(impl: NewsPreferencesDataSource): PreferencesDataSource

    @Binds
    @Singleton
    abstract fun bindNewsRepository(impl: DefaultNewsRepository): NewsRepository
}
