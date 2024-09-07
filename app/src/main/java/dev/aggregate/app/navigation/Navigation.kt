package dev.aggregate.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.aggregate.account.CreateNewPasswordScreen
import dev.aggregate.account.ForgotPasswordScreen
import dev.aggregate.account.SignInScreen
import dev.aggregate.account.SignUpScreen
import dev.aggregate.account.VerificationCodeScreen
import dev.aggregate.article.ArticleScreen
import dev.aggregate.bookmarks.BookmarksScreen
import dev.aggregate.favorites.FavoritesScreen
import dev.aggregate.profile.AppLanguageScreen
import dev.aggregate.profile.ChangePasswordScreen
import dev.aggregate.profile.PrivacyScreen
import dev.aggregate.profile.ProfileScreen
import dev.aggregate.profile.TermsAndConditionsScreen
import dev.aggregate.sources.SourcesScreen
import dev.aggregate.topheadlines.TopHeadlinesScreen
import dev.aggregate.welcome.OnBoardingScreen
import dev.aggregate.welcome.SelectFavoriteTopicsScreen

@Composable
@Suppress("LongMethod")
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
                VerificationCodeScreen(
                    navController = navController,
                    email = email
                )
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
