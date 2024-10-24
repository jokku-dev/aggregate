package dev.aggregate.model

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val favoriteCategory: String? = null,
    val favoriteCountry: String? = null,
    val sources: List<String> = emptyList(),
    val bookmarkedArticleIds: Set<String> = emptySet(),
    val viewedArticleIds: Set<String> = emptySet(),
    val topCategoryType: TopCategoryType = TopCategoryType.COUNTRY,
    val darkThemeConfig: DarkThemeConfig = DarkThemeConfig.FOLLOW_SYSTEM,
    val launchScreen: String = "",
    val userLoggedIn: Boolean = false
)
