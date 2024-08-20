package dev.jokku.aggregate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.jokku.aggregate.presentation.navigation.Screen
import dev.jokku.article.ArticleScreen
import dev.jokku.bookmarks.BookmarksScreen
import dev.jokku.favorites.FavoritesScreen
import dev.jokku.topheadlines.TopHeadlinesScreen
import dev.jokku.sources.SourcesScreen
import dev.jokku.account.CreateNewPasswordScreen
import dev.jokku.account.ForgotPasswordScreen
import dev.jokku.account.SignInScreen
import dev.jokku.account.SignUpScreen
import dev.jokku.profile.AppLanguageScreen
import dev.jokku.profile.ChangePasswordScreen
import dev.jokku.profile.PrivacyScreen
import dev.jokku.profile.ProfileScreen
import dev.jokku.profile.TermsAndConditionsScreen
import dev.jokku.welcome.OnBoardingScreen
import dev.jokku.welcome.SelectFavoriteTopicsScreen
import kotlin.let

@Composable
fun Navigation(navController: NavHostController, startDestination: String) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screen.OnBoarding.route) {
            dev.jokku.welcome.OnBoardingScreen(navController = navController)
        }
        composable(route = Screen.SelectFavoriteTopics.route) {
            dev.jokku.welcome.SelectFavoriteTopicsScreen(navController = navController)
        }
        composable(route = Screen.SignIn.route) {
            dev.jokku.account.SignInScreen(navController = navController)
        }
        composable(route = Screen.ForgotPassword.route) {
            dev.jokku.account.ForgotPasswordScreen(navController = navController)
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
                dev.jokku.account.VerificationCodeScreen(
                    navController = navController,
                    email = email
                )
            }
        }
        composable(route = Screen.CreateNewPassword.route) {
            dev.jokku.account.CreateNewPasswordScreen(navController = navController)
        }
        composable(route = Screen.SignUp.route) {
            dev.jokku.account.SignUpScreen(navController = navController)
        }


        composable(route = Screen.TopHeadlines.route) {
            TopHeadlinesScreen(navController = navController)
        }
        composable(route = Screen.Favorites.route) {
            dev.jokku.favorites.FavoritesScreen(navController = navController)
        }
        composable(route = Screen.Sources.route) {
            dev.jokku.sources.SourcesScreen(navController = navController)
        }
        composable(route = Screen.Bookmarks.route) {
            dev.jokku.bookmarks.BookmarksScreen(navController = navController)
        }
        composable(route = Screen.Profile.route) {
            dev.jokku.profile.ProfileScreen(navController = navController)
        }

        composable(route = Screen.ArticleScreen.route) {
            dev.jokku.article.ArticleScreen(navController = navController)
        }

        composable(route = Screen.Language.route) {
            dev.jokku.profile.AppLanguageScreen(navController = navController)
        }
        composable(route = Screen.ChangePassword.route) {
            dev.jokku.profile.ChangePasswordScreen(navController = navController)
        }
        composable(route = Screen.Privacy.route) {
            dev.jokku.profile.PrivacyScreen(navController = navController)
        }
        composable(route = Screen.TermsAndConditions.route) {
            dev.jokku.profile.TermsAndConditionsScreen(navController = navController)
        }
    }
}