package com.jokku.aggregate.ui.nav

sealed class Screen(val route: String) {
    object Welcome: Screen("welcome_screen")
    object Home: Screen("home_screen")
    object SignIn: Screen("signIn_screen")
}
