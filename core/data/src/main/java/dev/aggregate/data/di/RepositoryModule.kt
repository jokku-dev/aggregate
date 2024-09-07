package dev.aggregate.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.PreferencesSerializer
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.aggregate.data.repository.DefaultNewsRepository
import dev.aggregate.data.repository.NewsRepository
import dev.aggregate.datastore.NewsPreferencesDataSource
import dev.aggregate.datastore.PreferencesDataSource
import dev.aggregate.datastore.model.UserData
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

    companion object {
        private const val USER_PREFERENCES = "user-preferences"
        private const val USER_TYPED_PREFERENCES = "user-typed-preferences"

        @Provides
        @Singleton
        fun providePreferencesDataStore(
            @ApplicationContext applicationContext: Context
        ): DataStore<Preferences> = PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler { emptyPreferences() },
            produceFile = { applicationContext.preferencesDataStoreFile(USER_PREFERENCES) }
        )

        @Provides
        @Singleton
        fun provideProtoDataStore(
            @ApplicationContext applicationContext: Context
        ): DataStore<UserData> = DataStoreFactory.create(
            serializer = PreferencesSerializer,
            corruptionHandler = ReplaceFileCorruptionHandler { UserData() },
            produceFile = { applicationContext.dataStoreFile(USER_TYPED_PREFERENCES) }
        )
    }
}
