package com.jokku.aggregate.ui.nav

import com.jokku.aggregate.R

sealed class Screen(val route: String) {
    object Welcome: Screen("welcome")
    object SelectFavoriteTopics: Screen("selectFavoriteTopics")

    object Home: BottomNavigationScreen(
        route = "home", icon = R.drawable.ic_outline_home
    )
    object Categories: BottomNavigationScreen(
        route = "categories", icon = R.drawable.ic_outline_category
    )
    object Bookmarks: BottomNavigationScreen(
        route = "bookmarks", icon = R.drawable.ic_outline_bookmarks, badgeCount = 12
    )
    object Profile: BottomNavigationScreen(
        route = "profile", icon = R.drawable.ic_outline_person)

    object SignIn: Screen("signIn")
    object SignUp: Screen("signUp")
    object ForgotPassword: Screen("forgotPassword")
    object Verification: Screen("verification")
    object CreateNewPassword: Screen("createNewPassword")

    object Language: Screen("language")
    object ChangePassword: Screen("changePassword")
    object Article: Screen("article")

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