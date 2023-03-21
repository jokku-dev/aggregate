package com.jokku.aggregate.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
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
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferences")

@Singleton
class BasicDataStoreRepository @Inject constructor(
    context: Context
) : DataStoreRepository {

    private companion object {
        val screenAfterLaunchKey = stringPreferencesKey(name = "launch_screen")
    }

    private val dataStore = context.dataStore

    override suspend fun setLaunchScreen(screen: String) {
        dataStore.edit { preferences -> preferences[screenAfterLaunchKey] = screen }
    }

    override fun readLaunchScreen(): Flow<String> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) emit(emptyPreferences())
                else throw exception
            }
            .map { preferences ->
                preferences[screenAfterLaunchKey] ?: Screen.Welcome.route
            }
    }
}