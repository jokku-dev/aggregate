package dev.jokku.newsdata

import dev.jokku.aggregate.data.local.preferences.PreferencesDataSource
import dev.jokku.newsdb.preferences.model.DarkThemeConfig
import dev.jokku.newsdb.preferences.model.UserData
import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    val userData: Flow<UserData>

    suspend fun setPreferredCountries(countryCodes: Set<dev.jokku.aggregate.data.CountryCode>)
    suspend fun togglePreferredCountries(countryCode: dev.jokku.aggregate.data.CountryCode, preferred: Boolean)
    suspend fun setPreferredCategories(categories: Set<dev.jokku.aggregate.data.CategoryCode>)
    suspend fun togglePreferredCategories(categoryCode: dev.jokku.aggregate.data.CategoryCode, preferred: Boolean)
    suspend fun updateBookmarkedArticles(article: dev.jokku.aggregate.presentation.model.UiArticle, bookmarked: Boolean)
    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig)
    suspend fun setLaunchScreen(screen: String)
    suspend fun setUserLoggedIn(loggedIn: Boolean)
}

class UserPreferencesRepository @javax.inject.Inject constructor(
    private val preferences: PreferencesDataSource
) : PreferencesRepository {

    override val userData: Flow<UserData> = preferences.userData

    override suspend fun setPreferredCountries(countryCodes: Set<dev.jokku.aggregate.data.CountryCode>) {
        preferences.setPreferredCountries(countryCodes)
    }

    override suspend fun togglePreferredCountries(countryCode: dev.jokku.aggregate.data.CountryCode, preferred: Boolean) {
        preferences.togglePreferredCountries(countryCode, preferred)
    }

    override suspend fun setPreferredCategories(categories: Set<dev.jokku.aggregate.data.CategoryCode>) {
        preferences.setPreferredCategories(categories)
    }

    override suspend fun togglePreferredCategories(categoryCode: dev.jokku.aggregate.data.CategoryCode, preferred: Boolean) {
        preferences.togglePreferredCategories(categoryCode, preferred)
    }

    override suspend fun updateBookmarkedArticles(article: dev.jokku.aggregate.presentation.model.UiArticle, bookmarked: Boolean) {
        preferences.updateBookmarkedArticles(article, bookmarked)
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