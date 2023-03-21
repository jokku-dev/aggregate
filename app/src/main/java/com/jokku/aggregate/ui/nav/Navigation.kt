package com.jokku.aggregate.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jokku.aggregate.ui.screens.HomeScreen
import com.jokku.aggregate.ui.screens.SignInScreen
import com.jokku.aggregate.ui.screens.WelcomeScreen

@Composable
fun Navigation(navController: NavHostController, startDestination: String) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(navController = navController)
        }
        composable(route = Screen.SignIn.route) {
            SignInScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }

    }
}