package dev.jokku.newsdb.preferences.model

@Serializable
data class UserData(
    val categoryCodes: Set<dev.jokku.aggregate.data.CategoryCode> = emptySet(),
    val countryCodes: Set<dev.jokku.aggregate.data.CountryCode> = emptySet(),
    val bookmarkedArticleIds: Set<String> = emptySet(),
    val topCategoryType: TopCategoryType = TopCategoryType.COUNTRY,
    val darkThemeConfig: DarkThemeConfig = DarkThemeConfig.FOLLOW_SYSTEM,
    val launchScreen: String = "",
    val userLoggedIn: Boolean = false
)

enum class DarkThemeConfig {
    FOLLOW_SYSTEM, LIGHT, DARK
}

enum class TopCategoryType {
    COUNTRY, CATEGORY
}