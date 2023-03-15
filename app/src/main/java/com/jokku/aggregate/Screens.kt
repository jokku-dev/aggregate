package com.jokku.aggregate

sealed class Screens(val route: String) {
    object Onboarding: Screens("splash_screen")
    object Home: Screens("home_screen")
}
