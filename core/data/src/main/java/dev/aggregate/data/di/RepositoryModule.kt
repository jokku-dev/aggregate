package dev.aggregate.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.aggregate.data.repository.CompositeNewsPreferencesRepository
import dev.aggregate.data.repository.DefaultNewsRepository
import dev.aggregate.data.repository.NewsPreferencesRepository
import dev.aggregate.data.repository.NewsRepository
import dev.aggregate.data.repository.PreferencesRepository
import dev.aggregate.data.repository.UserPreferencesRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindPreferencesRepository(impl: UserPreferencesRepository): PreferencesRepository

    @Binds
    @Singleton
    abstract fun bindNewsRepository(impl: DefaultNewsRepository): NewsRepository

    @Binds
    @Singleton
    abstract fun bindCompositeNewsPreferencesRepository(
        impl: CompositeNewsPreferencesRepository
    ): NewsPreferencesRepository
}
