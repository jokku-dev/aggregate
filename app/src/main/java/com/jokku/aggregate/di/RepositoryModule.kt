package com.jokku.aggregate.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.jokku.aggregate.data.ProtoPreferences
import com.jokku.aggregate.data.CategoryPreferencesSerializer
import com.jokku.aggregate.data.remote.NewsRemoteDataSource
import com.jokku.aggregate.data.remote.RemoteDataSource
import com.jokku.aggregate.data.repo.DataStoreRepository
import com.jokku.aggregate.data.repo.MainDataStoreRepository
import com.jokku.aggregate.data.repo.MainNewsRepository
import com.jokku.aggregate.data.repo.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val USER_PREFERENCES = "user-preferences"
private const val USER_TYPED_PREFERENCES = "user-typed-preferences"

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDataStoreRepository(impl: MainDataStoreRepository): DataStoreRepository

    @Binds
    @Singleton
    abstract fun bindNewsRepository(impl: MainNewsRepository): NewsRepository

    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(impl: NewsRemoteDataSource): RemoteDataSource

    companion object {
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
        ) : DataStore<ProtoPreferences> = DataStoreFactory.create(
            serializer = CategoryPreferencesSerializer,
            corruptionHandler = ReplaceFileCorruptionHandler { ProtoPreferences() },
            produceFile = { applicationContext.dataStoreFile(USER_TYPED_PREFERENCES) }
        )
    }

}