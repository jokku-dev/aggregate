package com.jokku.aggregate.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.jokku.aggregate.ui.entity.Topic
import com.jokku.aggregate.ui.nav.Screen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

interface DataStoreRepository {
    suspend fun setLaunchScreen(screen: String)
    fun readLaunchScreen(): Flow<String>
    suspend fun setFavoriteTopics(topics: List<Topic>)
    fun readFavoriteTopics(): Flow<List<Topic>>
}

@Singleton
class BasicDataStoreRepository @Inject constructor(
    private val preferences: DataStore<Preferences>,
    private val proto: DataStore<TypedPreferences>
) : DataStoreRepository {

    private companion object {
        val screenAfterLaunchKey = stringPreferencesKey(name = "after_launch_screen")
    }

    override suspend fun setLaunchScreen(screen: String) {
        preferences.edit { preferences -> preferences[screenAfterLaunchKey] = screen }
    }
    override fun readLaunchScreen(): Flow<String> = preferences.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences())
            else throw exception
        }
        .map { preferences ->
            preferences[screenAfterLaunchKey] ?: Screen.OnBoarding.route
        }

    override suspend fun setFavoriteTopics(topics: List<Topic>) {
        proto.updateData { preferences ->
            preferences.copy(topics = topics)
        }
    }
    override fun readFavoriteTopics(): Flow<List<Topic>> = proto.data
        .catch { exception ->
            if (exception is IOException) emit(TypedPreferences())
            else throw exception
        }
        .map { preferences ->
            preferences.topics
        }
}