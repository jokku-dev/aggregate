package com.jokku.aggregate.data.repo

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.jokku.aggregate.R
import com.jokku.aggregate.data.ProtoPreferences
import com.jokku.aggregate.presentation.nav.Screen
import com.jokku.aggregate.domain.Result
import com.jokku.aggregate.presentation.model.UiErrorMessage
import com.jokku.aggregate.presentation.model.UiSelectableCategory
import com.jokku.aggregate.presentation.model.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

interface DataStoreRepository {
    suspend fun setLaunchScreen(screen: String)
    fun readLaunchScreen(): Flow<Result<String>>
    suspend fun setFavoriteTopics(categories: List<UiSelectableCategory>)
    fun readFavoriteCategories(): Flow<Result<List<UiSelectableCategory>>>
    suspend fun setUserLoggedIn(logged: Boolean)
    fun readUserLoggedIn(): Flow<Result<Boolean>>
}

@Singleton
class MainDataStoreRepository @Inject constructor(
    private val preferences: DataStore<Preferences>,
    private val proto: DataStore<ProtoPreferences>
) : DataStoreRepository {

    private companion object {
        val screenAfterLaunchKey = stringPreferencesKey(name = "after_launch_screen")
        val userLoggedIn = booleanPreferencesKey(name = "user_logged_in")
    }

    override suspend fun setLaunchScreen(screen: String) {
        preferences.edit { preferences -> preferences[screenAfterLaunchKey] = screen }
    }

    override fun readLaunchScreen(): Flow<Result<String>> = preferences.data
        .catchIOException()
        .map { preferences ->
            Result.Success(
                data = preferences[screenAfterLaunchKey] ?: Screen.OnBoarding.route,
                source = DataSourceType.PREFERENCES
            )
        }


    override suspend fun setFavoriteTopics(categories: List<UiSelectableCategory>) {
        proto.updateData { preferences ->
            preferences.copy(categories = categories) }
    }

    override fun readFavoriteCategories(): Flow<Result<List<UiSelectableCategory>>> = proto.data
        .catch { exception ->
            if (exception is IOException) emit(ProtoPreferences())
            else throw exception
        }
        .map { preferences -> preferences.categories }


    override suspend fun setUserLoggedIn(logged: Boolean) {
        preferences.edit { preferences -> preferences[userLoggedIn] = logged }
    }

    override fun readUserLoggedIn(): Flow<Result<Boolean>> = preferences.data
        .catchIOException()
        .map { preferences -> preferences[userLoggedIn] ?: false }
}

@Suppress("UNCHECKED_CAST")
fun <T> Flow<T>.catchIOException(): Flow<T> = this.catch { exception ->
    val failureState = if (exception is IOException) {
        Result.Failure(UiErrorMessage(UiText.StringResource(R.string.data_fetch_error))) as T
    } else {
        Result.Failure(UiErrorMessage(UiText.StringResource(R.string.unknown_error))) as T
    }
    emit(failureState)
}
