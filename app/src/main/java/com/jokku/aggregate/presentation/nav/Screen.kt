package com.jokku.aggregate.presentation.nav

import com.jokku.aggregate.R

sealed class Screen(val route: String) {
    object OnBoarding: Screen("onBoarding")
    object SelectFavoriteTopics: Screen("selectFavoriteTopics")

    object Favorites: BottomNavigationScreen(
        route = "favorites", icon = R.drawable.ic_favorite
    )
    object TopHeadlines: BottomNavigationScreen(
        route = "topHeadlines", icon = R.drawable.ic_chart
    )
    object Sources: BottomNavigationScreen(
        route = "sources", icon = R.drawable.ic_sources
    )
    object Bookmarks: BottomNavigationScreen(
        route = "bookmarks", icon = R.drawable.ic_bookmark, badgeCount = 12
    )
    object Profile: BottomNavigationScreen(
        route = "profile", icon = R.drawable.ic_profile
    )

    object SignIn: Screen("signIn")
    object SignUp: Screen("signUp")
    object ForgotPassword: Screen("forgotPassword")
    object Verification: Screen("verification")
    object CreateNewPassword: Screen("createNewPassword")

    object Language: Screen("language")
    object ChangePassword: Screen("changePassword")
    object Privacy: Screen("privacy")
    object TermsAndConditions: Screen("termsAndConditions")
    object ArticleScreen: Screen("article")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg -> append("/$arg") }
        }
    }
}

sealed class BottomNavigationScreen(
    route: String,
    val icon: Int,
    val badgeCount: Int = 0
) : Screen(route)