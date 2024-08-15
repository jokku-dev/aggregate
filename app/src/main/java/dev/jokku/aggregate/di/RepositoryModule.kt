package dev.jokku.aggregate.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dev.jokku.aggregate.data.local.preferences.model.UserData
import dev.jokku.aggregate.data.local.preferences.PreferencesSerializer
import dev.jokku.aggregate.data.remote.NewsRemoteDataSource
import dev.jokku.aggregate.data.remote.RemoteDataSource
import dev.jokku.aggregate.data.local.preferences.PreferencesDataSource
import dev.jokku.aggregate.data.local.preferences.NewsPreferencesDataSource
import dev.jokku.aggregate.data.repo.DefaultNewsRepository
import dev.jokku.aggregate.data.repo.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
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

    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(impl: NewsRemoteDataSource): RemoteDataSource

    companion object {
        private const val USER_PREFERENCES = "user-preferences"
        private const val USER_TYPED_PREFERENCES = "user-typed-preferences"

        @Provides
        @Singleton
        fun providePreferencesDataStore(
            @ApplicationContext applicationContext: Context
        ) : DataStore<Preferences> = PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler { emptyPreferences() },
            produceFile = { applicationContext.preferencesDataStoreFile(USER_PREFERENCES) }
        )

        @Provides
        @Singleton
        fun provideProtoDataStore(
            @ApplicationContext applicationContext: Context
        ) : DataStore<UserData> = DataStoreFactory.create(
            serializer = PreferencesSerializer,
            corruptionHandler = ReplaceFileCorruptionHandler { UserData() },
            produceFile = { applicationContext.dataStoreFile(USER_TYPED_PREFERENCES) }
        )
    }
}