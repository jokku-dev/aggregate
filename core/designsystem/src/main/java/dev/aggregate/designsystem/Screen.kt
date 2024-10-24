package dev.aggregate.designsystem

const val HOME = "topHeadlines"
const val FAVORITES = "favorites"
const val SOURCES = "sources"
const val BOOKMARKS = "bookmarks"
const val PROFILE = "profile"

sealed class Screen(val route: String) {
    object OnBoarding : Screen("onBoarding")
    object SelectFavoriteTopics : Screen("selectFavoriteTopics")

    object Favorites : BottomNavigationScreen(
        route = FAVORITES,
        icon = R.drawable.ic_favorite
    )

    object TopHeadlines : BottomNavigationScreen(
        route = HOME,
        icon = R.drawable.ic_chart
    )

    object Sources : BottomNavigationScreen(
        route = SOURCES,
        icon = R.drawable.ic_sources
    )

    object Bookmarks : BottomNavigationScreen(
        route = BOOKMARKS,
        icon = R.drawable.ic_bookmark,
        badgeCount = 12
    )

    object Profile : BottomNavigationScreen(
        route = PROFILE,
        icon = R.drawable.ic_profile
    )

    object SignIn : Screen("signIn")
    object SignUp : Screen("signUp")
    object ForgotPassword : Screen("forgotPassword")
    object Verification : Screen("verification")
    object CreateNewPassword : Screen("createNewPassword")

    object Language : Screen("language")
    object ChangePassword : Screen("changePassword")
    object Privacy : Screen("privacy")
    object TermsAndConditions : Screen("termsAndConditions")
    object ArticleScreen : Screen("article")

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
