package com.jokku.aggregate.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jokku.aggregate.ui.screens.BookmarksScreen
import com.jokku.aggregate.ui.screens.HomepageScreen
import com.jokku.aggregate.ui.screens.SourcesScreen
import com.jokku.aggregate.ui.screens.account.CreateNewPasswordScreen
import com.jokku.aggregate.ui.screens.account.ForgotPasswordScreen
import com.jokku.aggregate.ui.screens.account.SignInScreen
import com.jokku.aggregate.ui.screens.account.SignUpScreen
import com.jokku.aggregate.ui.screens.account.VerificationCodeScreen
import com.jokku.aggregate.ui.screens.preferences.AppLanguageScreen
import com.jokku.aggregate.ui.screens.preferences.ChangePasswordScreen
import com.jokku.aggregate.ui.screens.preferences.PrivacyScreen
import com.jokku.aggregate.ui.screens.preferences.ProfileScreen
import com.jokku.aggregate.ui.screens.preferences.TermsAndConditionsScreen
import com.jokku.aggregate.ui.screens.welcome.OnBoardingScreen
import com.jokku.aggregate.ui.screens.welcome.SelectFavoriteTopicsScreen

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
        composable(route = Screen.Sources.route) {
            SourcesScreen(navController = navController)
        }
        composable(route = Screen.Bookmarks.route) {
            BookmarksScreen(navController = navController)
        }
        composable(route = Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }

        composable(route = Screen.Language.route) {
            AppLanguageScreen(navController = navController)
        }
        composable(route = Screen.ChangePassword.route) {
            ChangePasswordScreen(navController = navController)
        }
        composable(route = Screen.Privacy.route) {
            PrivacyScreen(navController = navController)
        }
        composable(route = Screen.TermsAndConditions.route) {
            TermsAndConditionsScreen(navController = navController)
        }
    }
}