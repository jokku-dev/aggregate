package dev.aggregate.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import dev.aggregate.model.DarkThemeConfig
import dev.aggregate.model.UserData
import dev.aggregate.model.network.Category
import dev.aggregate.model.network.Country
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

    suspend fun setPreferredCountry(country: Country)
//    suspend fun togglePreferredCountries(country: Country, preferred: Boolean)
    suspend fun setPreferredCategory(category: Category)
//    suspend fun togglePreferredCategories(category: Category, preferred: Boolean)
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
        if (exception is IOException) {
            Log.e(USER_PREFERENCES, FAILED_TO_READ_USER_PREFERENCES, exception)
        }
    }

    override suspend fun setPreferredCountry(country: Country) {
        try {
            preferences.updateData { data -> data.copy(countryCode = country) }
        } catch (exception: IOException) {
            logException(exception)
        }
    }

    /*override suspend fun togglePreferredCountries(country: Country) {
        try {
            preferences.updateData { data ->
                data.copy(
                    countryCode =
                    if (preferred) {
                        data.countryCodes.plusElement(country)
                    } else {
                        data.countryCodes.minusElement(country)
                    }
                )
            }
        } catch (exception: IOException) {
            logException(exception)
        }
    }*/

    override suspend fun setPreferredCategory(category: Category) {
        try {
            preferences.updateData { data -> data.copy(categoryCode = category) }
        } catch (exception: IOException) {
            logException(exception)
        }
    }

    /*override suspend fun togglePreferredCategories(categoryCode: String, preferred: Boolean) {
        try {
            preferences.updateData { data ->
                data.copy(
                    categoryCodes =
                    if (preferred) {
                        data.categoryCodes.plusElement(categoryCode)
                    } else {
                        data.categoryCodes.minusElement(categoryCode)
                    }
                )
            }
        } catch (exception: IOException) {
            logException(exception)
        }
    }*/

    override suspend fun updateBookmarkedArticles(articleId: String, bookmarked: Boolean) {
        try {
            preferences.updateData { data ->
                data.copy(
                    bookmarkedArticleIds =
                    if (bookmarked) {
                        data.bookmarkedArticleIds.plusElement(articleId)
                    } else {
                        data.bookmarkedArticleIds.minusElement(articleId)
                    }
                )
            }
        } catch (exception: IOException) {
            logException(exception)
        }
    }

    override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        try {
            preferences.updateData { data -> data.copy(darkThemeConfig = darkThemeConfig) }
        } catch (exception: IOException) {
            logException(exception)
        }
    }

    override suspend fun setLaunchScreen(screen: String) {
        try {
            preferences.updateData { data -> data.copy(launchScreen = screen) }
        } catch (exception: IOException) {
            logException(exception)
        }
    }

    override suspend fun setUserLoggedIn(loggedIn: Boolean) {
        try {
            preferences.updateData { data -> data.copy(userLoggedIn = loggedIn) }
        } catch (exception: IOException) {
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
