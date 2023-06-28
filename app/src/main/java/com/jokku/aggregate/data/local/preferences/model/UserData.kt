package com.jokku.aggregate.data.local.preferences.model

import com.jokku.aggregate.data.CategoryCode
import com.jokku.aggregate.data.CountryCode
import com.jokku.aggregate.presentation.model.UiArticle
import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val categoryCodes: Set<CategoryCode> = emptySet(),
    val countryCodes: Set<CountryCode> = emptySet(),
    val bookmarkedArticles: Set<UiArticle> = emptySet(),
    val darkThemeConfig: DarkThemeConfig = DarkThemeConfig.FOLLOW_SYSTEM,
    val launchScreen: String = "",
    val userLoggedIn: Boolean = false
)

enum class DarkThemeConfig {
    FOLLOW_SYSTEM, LIGHT, DARK
}