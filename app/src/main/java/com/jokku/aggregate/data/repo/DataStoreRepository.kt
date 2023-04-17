package com.jokku.aggregate.data.repo

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.jokku.aggregate.data.TypedPreferences
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
    suspend fun setUserLoggedIn(logged: Boolean)
    fun readUserLoggedIn(): Flow<Boolean>
}

@Singleton
class MainDataStoreRepository @Inject constructor(
    private val preferences: DataStore<Preferences>,
    private val proto: DataStore<TypedPreferences>
) : DataStoreRepository {

    private companion object {
        val screenAfterLaunchKey = stringPreferencesKey(name = "after_launch_screen")
        val userLoggedIn = booleanPreferencesKey(name = "user_logged_in")
    }

    override suspend fun setLaunchScreen(screen: String) {
        preferences.edit { preferences -> preferences[screenAfterLaunchKey] = screen }
    }

    override fun readLaunchScreen(): Flow<String> = preferences.data
        .catchIOException()
        .map { preferences -> preferences[screenAfterLaunchKey] ?: Screen.OnBoarding.route }


    override suspend fun setFavoriteTopics(topics: List<Topic>) {
        proto.updateData { preferences -> preferences.copy(topics = topics) }
    }

    override fun readFavoriteTopics(): Flow<List<Topic>> = proto.data
        .catch { exception ->
            if (exception is IOException) emit(TypedPreferences())
            else throw exception
        }
        .map { preferences -> preferences.topics }


    override suspend fun setUserLoggedIn(logged: Boolean) {
        preferences.edit { preferences -> preferences[userLoggedIn] = logged }
    }

    override fun readUserLoggedIn(): Flow<Boolean> = preferences.data
        .catchIOException()
        .map { preferences -> preferences[userLoggedIn] ?: false }
}

@Suppress("UNCHECKED_CAST")
fun <T> Flow<T>.catchIOException(): Flow<T> = this.catch { exception ->
    if (exception is IOException) emit(emptyPreferences() as T)
    else throw exception
}
