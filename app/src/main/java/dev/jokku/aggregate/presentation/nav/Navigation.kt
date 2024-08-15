package dev.jokku.aggregate.presentation.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.jokku.aggregate.presentation.screens.article.ArticleScreen
import dev.jokku.aggregate.presentation.screens.bookmarks.BookmarksScreen
import dev.jokku.aggregate.presentation.screens.favorites.FavoritesScreen
import dev.jokku.aggregate.presentation.screens.topheadlines.TopHeadlinesScreen
import dev.jokku.aggregate.presentation.screens.sources.SourcesScreen
import dev.jokku.aggregate.presentation.screens.account.CreateNewPasswordScreen
import dev.jokku.aggregate.presentation.screens.account.ForgotPasswordScreen
import dev.jokku.aggregate.presentation.screens.account.SignInScreen
import dev.jokku.aggregate.presentation.screens.account.SignUpScreen
import dev.jokku.aggregate.presentation.screens.account.VerificationCodeScreen
import dev.jokku.aggregate.presentation.screens.profile.AppLanguageScreen
import dev.jokku.aggregate.presentation.screens.profile.ChangePasswordScreen
import dev.jokku.aggregate.presentation.screens.profile.PrivacyScreen
import dev.jokku.aggregate.presentation.screens.profile.ProfileScreen
import dev.jokku.aggregate.presentation.screens.profile.TermsAndConditionsScreen
import dev.jokku.aggregate.presentation.screens.welcome.OnBoardingScreen
import dev.jokku.aggregate.presentation.screens.welcome.SelectFavoriteTopicsScreen

@Composable
fun Navigation(navController: NavHostController, startDestination: String) {
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


        composable(route = Screen.TopHeadlines.route) {
            TopHeadlinesScreen(navController = navController)
        }
        composable(route = Screen.Favorites.route) {
            FavoritesScreen(navController = navController)
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

        composable(route = Screen.ArticleScreen.route) {
            ArticleScreen(navController = navController)
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