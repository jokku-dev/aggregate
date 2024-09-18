package dev.aggregate.model

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val categoryCodes: Set<String> = emptySet(),
    val countryCodes: Set<String> = emptySet(),
    val bookmarkedArticleIds: Set<String> = emptySet(),
    val topCategoryType: TopCategoryType = TopCategoryType.COUNTRY,
    val darkThemeConfig: DarkThemeConfig = DarkThemeConfig.FOLLOW_SYSTEM,
    val launchScreen: String = "",
    val userLoggedIn: Boolean = false
)
