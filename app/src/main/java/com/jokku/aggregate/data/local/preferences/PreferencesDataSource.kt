package com.jokku.aggregate.data.local.preferences

import android.util.Log
import androidx.datastore.core.DataStore
import com.jokku.aggregate.data.CategoryCode
import com.jokku.aggregate.data.CountryCode
import com.jokku.aggregate.data.local.preferences.model.DarkThemeConfig
import com.jokku.aggregate.data.local.preferences.model.UserData
import com.jokku.aggregate.presentation.model.UiArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

const val USER_PREFERENCES = "UserPreferences"
const val FAILED_TO_UPDATE_USER_PREFERENCES = "Failed to update user preferences"
const val FAILED_TO_COPY_USER_PREFERENCES = "Failed to copy user preferences"
const val FAILED_TO_READ_USER_PREFERENCES = "Failed to read user preferences"

interface PreferencesDataSource {

    val userData: Flow<UserData>

    suspend fun setPreferredCountries(countryCodes: Set<CountryCode>)
    suspend fun togglePreferredCountries(countryCode: CountryCode, preferred: Boolean)
    suspend fun setPreferredCategories(categories: Set<CategoryCode>)
    suspend fun togglePreferredCategories(categoryCode: CategoryCode, preferred: Boolean)
    suspend fun updateBookmarkedArticles(articleId: String, bookmarked: Boolean)
    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig)
    suspend fun setLaunchScreen(screen: String)
    suspend fun setUserLoggedIn(loggedIn: Boolean)
}

@Singleton
class NewsPreferencesDataSource @Inject constructor(
    private val preferences: DataStore<UserData>
) : PreferencesDataSource {

    override val userData = preferences.data.catch { exception ->
        if (exception is IOException)
            Log.e(USER_PREFERENCES, FAILED_TO_READ_USER_PREFERENCES, exception)
    }

    override suspend fun setPreferredCountries(countryCodes: Set<CountryCode>) {
        try {
            preferences.updateData { data -> data.copy(countryCodes = countryCodes) }
        } catch (exception: Exception) {
            logException(exception)
        }
    }

    override suspend fun togglePreferredCountries(countryCode: CountryCode, preferred: Boolean) {
        try {
            preferences.updateData { data ->
                data.copy(
                    countryCodes =
                    if (preferred) data.countryCodes.plusElement(countryCode)
                    else data.countryCodes.minusElement(countryCode)
                )
            }
        } catch (exception: Exception) {
            logException(exception)
        }
    }

    override suspend fun setPreferredCategories(categoryCodes: Set<CategoryCode>) {
        try {
            preferences.updateData { data -> data.copy(categoryCodes = categoryCodes) }
        } catch (exception: Exception) {
            logException(exception)
        }
    }

    override suspend fun togglePreferredCategories(categoryCode: CategoryCode, preferred: Boolean) {
        try {
            preferences.updateData { data ->
                data.copy(
                    categoryCodes =
                    if (preferred) data.categoryCodes.plusElement(categoryCode)
                    else data.categoryCodes.minusElement(categoryCode)
                )
            }
        } catch (exception: Exception) {
            logException(exception)
        }
    }

    override suspend fun updateBookmarkedArticles(articleId: String, bookmarked: Boolean) {
        try {
            preferences.updateData { data ->
                data.copy(
                    bookmarkedArticleIds =
                    if (bookmarked) data.bookmarkedArticleIds.plusElement(articleId)
                    else data.bookmarkedArticleIds.minusElement(articleId)
                )
            }
        } catch (exception: Exception) {
            logException(exception)
        }
    }

    override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        try {
            preferences.updateData { data -> data.copy(darkThemeConfig = darkThemeConfig) }
        } catch (exception: Exception) {
            logException(exception)
        }
    }

    override suspend fun setLaunchScreen(screen: String) {
        try {
            preferences.updateData { data -> data.copy(launchScreen = screen) }
        } catch (exception: Exception) {
            logException(exception)
        }
    }

    override suspend fun setUserLoggedIn(loggedIn: Boolean) {
        try {
            preferences.updateData { data -> data.copy(userLoggedIn = loggedIn) }
        } catch (exception: Exception) {
            logException(exception)
        }
    }

    private fun logException(exception: Exception) {
        when (exception) {
            is IOException -> Log.e(USER_PREFERENCES, FAILED_TO_UPDATE_USER_PREFERENCES, exception)
            else -> Log.e(USER_PREFERENCES, FAILED_TO_COPY_USER_PREFERENCES, exception)
        }
    }
}