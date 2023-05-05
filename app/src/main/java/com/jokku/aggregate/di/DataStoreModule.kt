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
import com.jokku.aggregate.data.TypedPreferences
import com.jokku.aggregate.data.TypedPreferencesSerializer
import com.jokku.aggregate.data.repo.DataStoreRepository
import com.jokku.aggregate.data.repo.MainDataStoreRepository
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
abstract class DataStoreModule {

    @Binds
    @Singleton
    abstract fun bindDataStoreRepository(impl: MainDataStoreRepository): DataStoreRepository

    companion object {

        @Provides
        @Singleton
        fun providePreferencesDataStore(
            @ApplicationContext applicationContext: Context
        ) : DataStore<Preferences> = PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler { emptyPreferences() }
        ) {
            applicationContext.preferencesDataStoreFile(USER_PREFERENCES)
        }

        @Provides
        @Singleton
        fun provideProtoDataStore(
            @ApplicationContext applicationContext: Context
        ) : DataStore<TypedPreferences> = DataStoreFactory.create(
            serializer = TypedPreferencesSerializer,
            corruptionHandler = ReplaceFileCorruptionHandler { TypedPreferences() }
        ) {
            applicationContext.dataStoreFile(USER_TYPED_PREFERENCES)
        }
    }

}