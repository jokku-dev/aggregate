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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import dev.aggregate.designsystem.Screen
import dev.aggregate.designsystem.component.BookmarkedArticleItem
import dev.aggregate.designsystem.component.CommonColumn
import dev.aggregate.designsystem.component.HeadlineAndDescriptionText
import dev.aggregate.designsystem.theme.AggregateTheme
import dev.aggregate.model.ui.UiArticle
import dev.aggregate.model.ui.UiArticleSource


@Composable
fun BookmarksScreen(
    navController: NavHostController,
    viewModel: BookmarksViewModel = hiltViewModel<MainBookmarksViewModel>()
) {
    val state = viewModel.bookmarksState.collectAsStateWithLifecycle().value

    if (state is BookmarksState.Success) {
        BookmarksScreenContent(
            articles = state.stateData,
            openArticle = { article ->
//            viewModel.setChosenArticle(article)
                navController.navigate(route = Screen.ArticleScreen.route)
            }
        )
    }
}

@Composable
fun BookmarksScreenContent(
    articles: List<UiArticle>,
    openArticle: (UiArticle) -> Unit
) {
    CommonColumn {
        HeadlineAndDescriptionText(
            headline = stringResource(id = R.string.bookmarks),
            description = stringResource(id = R.string.saved_articles)
        )
        if (articles.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 96.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_empty_bookmarks_background),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_empty_bookmarks_foreground),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                Text(
                    text = stringResource(id = R.string.you_have_not_saved_any),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .width(256.dp)
                        .padding(top = 24.dp)
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(count = articles.size) {
                    BookmarkedArticleItem(
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
@Composable
fun BookmarksScreenPreview() {
    AggregateTheme {
        BookmarksScreenContent(
            articles = listOf(
                UiArticle(
                    author = "Oliver Darcy",
                    bookmarked = false,
                    content = "",
                    description = "",
                    publishedAt = "2023-04-25T08:36",
                    source = UiArticleSource(id = "", name = "The Washington Post"),
                    title = "Fox News' sudden firing of Tucker Carlson " +
                            "may have come down to one simple calculation - CNN",
                    url = "",
                    urlToImage = "",
                    viewed = false
                ),
                UiArticle(
                    author = "Oliver Darcy",
                    bookmarked = false,
                    content = "",
                    description = "",
                    publishedAt = "2023-04-25T08:36",
                    source = UiArticleSource(id = "", name = "The Washington Post"),
                    title = "Fox News' sudden firing of Tucker Carlson " +
                            "may have come down to one simple calculation - CNN",
                    url = "",
                    urlToImage = "",
                    viewed = false
                ),
                UiArticle(
                    author = "Oliver Darcy",
                    bookmarked = false,
                    content = "",
                    description = "",
                    publishedAt = "2023-04-25T08:36",
                    source = UiArticleSource(id = "", name = "The Washington Post"),
                    title = "Fox News' sudden firing of Tucker Carlson " +
                            "may have come down to one simple calculation - CNN",
                    url = "",
                    urlToImage = "",
                    viewed = false
                ),
            ),
            openArticle = {}
        )
    }
}
