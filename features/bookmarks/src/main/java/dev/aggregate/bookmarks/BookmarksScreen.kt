package dev.aggregate.bookmarks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import dev.aggregate.designsystem.theme.AggregateTheme
import dev.aggregate.presentation.model.UiArticle
import dev.aggregate.presentation.model.UiArticleSource
import dev.aggregate.presentation.navigation.Screen
import dev.aggregate.ui.BookmarkedArticleItem

@androidx.compose.runtime.Composable
fun BookmarksScreen(
    navController: NavHostController,
    viewModel: dev.aggregate.app.presentation.screens.bookmarks.BookmarksViewModel = hiltViewModel<dev.aggregate.app.presentation.screens.bookmarks.MainBookmarksViewModel>()
) {
    val state by viewModel.bookmarksState.collectAsStateWithLifecycle()

    BookmarksScreenContent(
        articles = state.bookmarkedArticles,
        openArticle = { article ->
//            viewModel.setChosenArticle(article)
            navController.navigate(route = dev.aggregate.app.presentation.navigation.Screen.ArticleScreen.route)
        }
    )
}

@androidx.compose.runtime.Composable
fun BookmarksScreenContent(
    articles: List<dev.aggregate.app.presentation.model.UiArticle>,
    openArticle: (dev.aggregate.app.presentation.model.UiArticle) -> Unit
) {
    dev.aggregate.app.ui.CommonColumn {
        dev.aggregate.app.ui.HeadlineAndDescriptionText(
            headline = androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.bookmarks),
            description = androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.saved_articles)
        )
        if (articles.isEmpty()) {
            Column(
                modifier = androidx.compose.ui.Modifier
                    .fillMaxSize()
                    .padding(bottom = 96.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
            ) {
                Box(contentAlignment = androidx.compose.ui.Alignment.Center) {
                    Icon(
                        imageVector = androidx.compose.ui.graphics.vector.ImageVector.vectorResource(
                            dev.aggregate.app.R.drawable.ic_empty_bookmarks_background
                        ),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary
                    )
                    Icon(
                        imageVector = androidx.compose.ui.graphics.vector.ImageVector.vectorResource(
                            dev.aggregate.app.R.drawable.ic_empty_bookmarks_foreground
                        ),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                Text(
                    text = androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.you_have_not_saved_any),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    modifier = androidx.compose.ui.Modifier
                        .width(256.dp)
                        .padding(top = 24.dp)
                )
            }
        } else {
            LazyColumn(
                modifier = androidx.compose.ui.Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
            ) {
                items(count = articles.size) {
                    dev.aggregate.app.ui.BookmarkedArticleItem(
                        urlToImage = articles[it].urlToImage,
                        title = articles[it].title,
                        sourceName = articles[it].source.name,
                        onItemClick = { openArticle(articles[it]) }
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@androidx.compose.runtime.Composable
fun BookmarksScreenPreview() {
    dev.aggregate.app.designsystem.theme.AggregateTheme {
        BookmarksScreenContent(
            articles = listOf(
                dev.aggregate.app.presentation.model.UiArticle(
                    source = dev.aggregate.app.presentation.model.UiArticleSource(name = "CNN"),
                    author = "Oliver Darcy",
                    title = "Fox News' sudden firing of Tucker Carlson may have come down to one simple calculation - CNN",
                    publishedAt = "2023-04-25T08:36",
                    bookmarked = false
                ),
                dev.aggregate.app.presentation.model.UiArticle(
                    source = dev.aggregate.app.presentation.model.UiArticleSource(name = "CNN"),
                    author = "Oliver Darcy",
                    title = "Fox News' sudden firing of Tucker Carlson may have come down to one simple calculation - CNN",
                    publishedAt = "2023-04-25T08:36",
                    bookmarked = false
                ),
                dev.aggregate.app.presentation.model.UiArticle(
                    source = dev.aggregate.app.presentation.model.UiArticleSource(name = "CNN"),
                    author = "Oliver Darcy",
                    title = "Fox News' sudden firing of Tucker Carlson may have come down to one simple calculation - CNN",
                    publishedAt = "2023-04-25T08:36",
                    bookmarked = false
                )
            ),
            openArticle = {}
        )
    }
}