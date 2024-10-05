package dev.aggregate.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.aggregate.data.LocalDataProvider
import dev.aggregate.data.LocalDataProviderFactory
import dev.aggregate.data.repository.CompositeNewsPreferencesRepository
import dev.aggregate.data.repository.DefaultNewsRepository
import dev.aggregate.data.repository.NewsPreferencesRepository
import dev.aggregate.data.repository.NewsRepository
import dev.aggregate.data.repository.PreferencesRepository
import dev.aggregate.data.repository.UserPreferencesRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindPreferencesRepository(impl: UserPreferencesRepository): PreferencesRepository

    @Binds
    abstract fun bindNewsRepository(impl: DefaultNewsRepository): NewsRepository

    @Binds
    abstract fun bindCompositeNewsPreferencesRepository(
        impl: CompositeNewsPreferencesRepository
    ): NewsPreferencesRepository

    @Binds
    abstract fun bindLocalDataProvider(impl: LocalDataProviderFactory): LocalDataProvider
}
