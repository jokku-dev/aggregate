package com.jokku.aggregate.ui.nav

sealed class Screen(val route: String) {
    object Welcome: Screen("welcome_screen")

    object SignIn: Screen("signIn_screen")
    object Verification: Screen("verification_screen")

    object ForgotPassword: Screen("forgotPassword_screen")
    object CreateNewPassword: Screen("createNewPassword_screen")

    object SignUp: Screen("signUp_screen")
    object SelectFavoriteTopics: Screen("selectFavoriteTopics_screen")

    object Home: Screen("home_screen")
    object Categories: Screen("categories_screen")
    object Bookmarks: Screen("bookmarks_screen")

    object Article: Screen("article_screen")

    object Profile: Screen("profile_screen")
    object Language: Screen("language_screen")
    object ChangePassword: Screen("changePassword_screen")
}