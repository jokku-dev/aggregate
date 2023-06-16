package com.jokku.aggregate.data.local.preferences

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.jokku.aggregate.data.local.preferences.model.ProtoModel
import com.jokku.aggregate.presentation.nav.Screen
import com.jokku.aggregate.presentation.model.UiCategory
import com.jokku.aggregate.presentation.screens.welcome.CategoryType
import com.jokku.aggregate.presentation.screens.welcome.LocalDataProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

const val DATA_STORE = "data_store"

interface PreferencesDataSource {
    suspend fun setLaunchScreen(screen: String)
    fun readLaunchScreen(): Flow<String>
    suspend fun setUserLoggedIn(logged: Boolean)
    fun readUserLoggedIn(): Flow<Boolean>
    suspend fun setFavoriteCategories(categories: List<UiCategory>, type: CategoryType)
    fun readFavoriteCategories(type: CategoryType): Flow<List<UiCategory>>
}

@Singleton
class NewsPreferencesDataSource @Inject constructor(
    private val preferences: DataStore<Preferences>,
    private val proto: DataStore<ProtoModel>,
    private val localDataProvider: LocalDataProvider
) : PreferencesDataSource {

    private companion object {
        val screenAfterLaunchKey = stringPreferencesKey(name = "after_launch_screen")
        val userLoggedIn = booleanPreferencesKey(name = "user_logged_in")
    }

    override suspend fun setLaunchScreen(screen: String) {
        preferences.edit { preferences -> preferences[screenAfterLaunchKey] = screen }
    }

    override fun readLaunchScreen(): Flow<String> = preferences.data
        .catch()
        .map { preferences -> preferences[screenAfterLaunchKey] ?: Screen.OnBoarding.route }

    override suspend fun setUserLoggedIn(logged: Boolean) {
        preferences.edit { preferences -> preferences[userLoggedIn] = logged }
    }

    override fun readUserLoggedIn(): Flow<Boolean> = preferences.data
        .catch()
        .map { preferences -> preferences[userLoggedIn] ?: false }

    override suspend fun setFavoriteCategories(
        categories: List<UiCategory>,
        type: CategoryType
    ) {
        proto.updateData { preferences ->
            when (type) {
                CategoryType.LANGUAGE -> preferences.copy(languages = categories)
                CategoryType.COUNTRY -> preferences.copy(countries = categories)
                CategoryType.CATEGORY -> preferences.copy(categories = categories)
            }
        }
    }

    override fun readFavoriteCategories(type: CategoryType): Flow<List<UiCategory>> = proto.data
        .catch { exception ->
            emit(localDataProvider.provideNewsCategoriesPreferences(type))
        }
        .map { preferences ->
            when (type) {
                CategoryType.LANGUAGE ->
                    preferences.languages.ifEmpty { localDataProvider.provideNewsCategoriesPreferences(type) }
                CategoryType.COUNTRY ->
                    preferences.countries.ifEmpty { localDataProvider.provideNewsCategoriesPreferences(type) }
                CategoryType.CATEGORY ->
                    preferences.categories.ifEmpty { localDataProvider.provideNewsCategoriesPreferences(type) }
            }
        }


}


fun <T> Flow<T>.catch(): Flow<T> = this.catch { exception ->
    Log.e(DATA_STORE, exception.message, exception)
}
