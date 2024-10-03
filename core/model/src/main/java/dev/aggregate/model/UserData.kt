package dev.aggregate.model

import dev.aggregate.model.network.Category
import dev.aggregate.model.network.Country
import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val categoryCode: Category? = null,
    val countryCode: Country? = null,
    val sources: List<String> = emptyList(),
    val bookmarkedArticleIds: Set<String> = emptySet(),
    val viewedArticleIds: Set<String> = emptySet(),
    val topCategoryType: TopCategoryType = TopCategoryType.COUNTRY,
    val darkThemeConfig: DarkThemeConfig = DarkThemeConfig.FOLLOW_SYSTEM,
    val launchScreen: String = "",
    val userLoggedIn: Boolean = false
)
