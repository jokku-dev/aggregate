package com.jokku.aggregate.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jokku.aggregate.ui.screens.BookmarksScreen
import com.jokku.aggregate.ui.screens.CategoriesScreen
import com.jokku.aggregate.ui.screens.CreateNewPasswordScreen
import com.jokku.aggregate.ui.screens.ForgotPasswordScreen
import com.jokku.aggregate.ui.screens.HomepageScreen
import com.jokku.aggregate.ui.screens.OnBoardingScreen
import com.jokku.aggregate.ui.screens.ProfileScreen
import com.jokku.aggregate.ui.screens.SelectFavoriteTopicsScreen
import com.jokku.aggregate.ui.screens.SignInScreen
import com.jokku.aggregate.ui.screens.SignUpScreen
import com.jokku.aggregate.ui.screens.VerificationCodeScreen

@Composable
fun Navigation(modifier: Modifier, navController: NavHostController, startDestination: String) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screen.OnBoarding.route) {
            OnBoardingScreen(navController = navController)
        }
        composable(route = Screen.SelectFavoriteTopics.route) {
            SelectFavoriteTopicsScreen(navController = navController)
        }
        composable(route = Screen.SignIn.route) {
            SignInScreen(navController = navController)
        }
        composable(route = Screen.ForgotPassword.route) {
            ForgotPasswordScreen(navController = navController)
        }
        composable(
            route = Screen.Verification.route + "/{email}",
            arguments = listOf(
                navArgument("email") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            entry.arguments?.getString("email")?.let { email ->
                VerificationCodeScreen(navController = navController, email = email)
            }
        }
        composable(route = Screen.CreateNewPassword.route) {
            CreateNewPasswordScreen(navController = navController)
        }
        composable(route = Screen.SignUp.route) {
            SignUpScreen(navController = navController)
        }


        composable(route = Screen.Home.route) {
            HomepageScreen(navController = navController)
        }
        composable(route = Screen.Categories.route) {
            CategoriesScreen(navController = navController)
        }
        composable(route = Screen.Bookmarks.route) {
            BookmarksScreen(navController = navController)
        }
        composable(route = Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }

    }
}