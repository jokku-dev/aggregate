package dev.aggregate.data.repository

import dev.aggregate.data.CategoryCode
import dev.aggregate.data.CountryCode
import dev.aggregate.model.DarkThemeConfig
import dev.aggregate.model.UserData
import dev.aggregate.datastore.PreferencesDataSource
import dev.aggregate.model.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PreferencesRepository {
    val userData: Flow<UserData>

    suspend fun setPreferredCountries(countryCodes: Set<CountryCode>)
    suspend fun togglePreferredCountries(countryCode: CountryCode, preferred: Boolean)
    suspend fun setPreferredCategories(categories: Set<CategoryCode>)
    suspend fun togglePreferredCategories(categoryCode: CategoryCode, preferred: Boolean)
    suspend fun updateBookmarkedArticles(article: Article, bookmarked: Boolean)
    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig)
    suspend fun setLaunchScreen(screen: String)
    suspend fun setUserLoggedIn(loggedIn: Boolean)
}

class UserPreferencesRepository @Inject constructor(
    private val preferences: PreferencesDataSource
) : PreferencesRepository {

    override val userData: Flow<UserData> = preferences.userData

    override suspend fun setPreferredCountries(countryCodes: Set<CountryCode>) {
        preferences.setPreferredCountries(countryCodes)
    }

    override suspend fun togglePreferredCountries(countryCode: CountryCode, preferred: Boolean) {
        preferences.togglePreferredCountries(countryCode, preferred)
    }

    override suspend fun setPreferredCategories(categories: Set<CategoryCode>) {
        preferences.setPreferredCategories(categories)
    }

    override suspend fun togglePreferredCategories(categoryCode: CategoryCode, preferred: Boolean) {
        preferences.togglePreferredCategories(categoryCode, preferred)
    }

    override suspend fun updateBookmarkedArticles(article: Article, bookmarked: Boolean) {
//        preferences.updateBookmarkedArticles(article, bookmarked)
        TODO()
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
