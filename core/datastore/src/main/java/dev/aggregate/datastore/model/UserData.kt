package dev.aggregate.datastore.model

@Serializable
data class UserData(
    val categoryCodes: Set<dev.aggregate.app.data.CategoryCode> = emptySet(),
    val countryCodes: Set<dev.aggregate.app.data.CountryCode> = emptySet(),
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
