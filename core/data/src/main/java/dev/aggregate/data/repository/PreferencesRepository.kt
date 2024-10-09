package dev.aggregate.data.repository

import dev.aggregate.datastore.PreferencesDataSource
import dev.aggregate.model.DarkThemeConfig
import dev.aggregate.model.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PreferencesRepository {
    val userData: Flow<UserData>

    suspend fun setPreferredCountry(countryCode: String)
//    suspend fun togglePreferredCountries(countryCode: String, preferred: Boolean)
    suspend fun setFavoriteCategory(categoryCode: String?)
//    suspend fun togglePreferredCategories(categoryCode: String, preferred: Boolean)
    suspend fun updateBookmarkedArticles(articleId: String, bookmarked: Boolean)
    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig)
    suspend fun setLaunchScreen(screen: String)
    suspend fun setUserLoggedIn(loggedIn: Boolean)
}

class UserPreferencesRepository @Inject constructor(
    private val preferences: PreferencesDataSource
) : PreferencesRepository {

    override val userData: Flow<UserData> = preferences.userData

    override suspend fun setPreferredCountry(countryCode: String) {
        preferences.setPreferredCountry(countryCode)
    }

    /*override suspend fun togglePreferredCountries(countryCode: String, preferred: Boolean) {
        preferences.togglePreferredCountries(countryCode, preferred)
    }*/

    override suspend fun setFavoriteCategory(categoryCode: String?) {
        preferences.setFavoriteCategory(categoryCode)
    }

//    override suspend fun togglePreferredCategories(categoryCode: String, preferred: Boolean) {
//        preferences.togglePreferredCategories(categoryCode, preferred)
//    }

    override suspend fun updateBookmarkedArticles(articleId: String, bookmarked: Boolean) {
        preferences.updateBookmarkedArticles(articleId, bookmarked)
    }

    override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        preferences.setDarkThemeConfig(darkThemeConfig)
    }

    override suspend fun setLaunchScreen(screen: String) {
        preferences.setLaunchScreen(screen)
    }

    override suspend fun setUserLoggedIn(loggedIn: Boolean) {
        preferences.setUserLoggedIn(loggedIn)
    }
}
