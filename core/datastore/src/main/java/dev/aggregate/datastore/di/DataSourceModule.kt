package dev.aggregate.datastore.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.aggregate.datastore.NewsPreferencesDataSource
import dev.aggregate.datastore.PreferencesDataSource

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun bindPreferencesDataSource(impl: NewsPreferencesDataSource): PreferencesDataSource
}
