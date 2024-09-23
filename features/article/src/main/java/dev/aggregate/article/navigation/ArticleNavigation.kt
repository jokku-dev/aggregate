package dev.aggregate.article.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import dev.aggregate.article.ArticleScreen
import dev.aggregate.designsystem.Screen
import kotlinx.serialization.Serializable

@Serializable
data class ArticleRoute(val id: String)

fun NavController.navigateToArticle(
    articleId: String,
    navOptions: NavOptionsBuilder.() -> Unit = {}
) {
    navigate(route = ArticleRoute(articleId)) {
        navOptions()
    }
}

fun NavGraphBuilder.articleScreen(
    showBackButton: Boolean,
    onBackClick: () -> Unit,
    onTopicClick: (String) -> Unit,
) {
    composable<ArticleRoute> {
        ArticleScreen(
            showBackButton = showBackButton,
            onBackClick = onBackClick,
            onTopicClick = onTopicClick,
        )
    }
}
