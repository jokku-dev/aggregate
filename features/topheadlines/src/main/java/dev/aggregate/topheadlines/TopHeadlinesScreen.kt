package dev.aggregate.topheadlines

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import dev.aggregate.app.R
import dev.aggregate.designsystem.theme.AggregateTheme
import dev.aggregate.ui.ArticleItem
import dev.aggregate.ui.CategoryItem
import dev.aggregate.ui.HeadlineAndDescriptionText
import dev.aggregate.ui.SearchTextField
import dev.aggregate.ui.UiArticle
import dev.aggregate.ui.UiCategory

@Composable
fun TopHeadlinesScreen(
    navController: NavHostController,
    viewModel: TopHeadlinesViewModel = hiltViewModel<MainTopHeadlinesViewModel>()
) { 
    val state by viewModel.topHeadlinesState.collectAsStateWithLifecycle()

    var search by rememberSaveable { mutableStateOf("") }

    when (val currentState = state) {
        is TopHeadlinesState.Failure -> TopHeadlinesWithError()
        is TopHeadlinesState.Loading -> ArticlesDuringUpdate()
        is TopHeadlinesState.None -> EmptyTopHeadlines()
        is TopHeadlinesState.Success -> TopHeadlinesScreenContent(
            categories = currentState.stateData.categories,
            uiArticles = currentState.stateData.articles,
            search = search,
            onSearchChanged = { newSearch -> search = newSearch},
            selectCategory = { category ->
                viewModel.selectCategory(category)
            },
            keyboardAction = {},
            onArticleClick = {}
        )
    }
}

@Composable
fun TopHeadlinesScreenContent(
    categories: List<UiCategory>,
    uiArticles: List<UiArticle>,
    search: String,
    onSearchChanged: (String) -> Unit,
    selectCategory: (UiCategory) -> Unit,
    keyboardAction: KeyboardActionScope.() -> Unit,
    onArticleClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .systemBarsPadding()
            .padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        HeadlineAndDescriptionText(
            headline = stringResource(id = R.string.browse),
            description = stringResource(id = R.string.discover_things),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        SearchTextField(
            search = search,
            onSearchChange = { newSearch -> onSearchChanged(newSearch) },
            keyboardAction = keyboardAction,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 32.dp)
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 24.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(count = categories.size) {
                CategoryItem(
                    text = stringResource(id = TODO("Not Implemented")),
                    selected = categories[it].selected,
                    onItemClick = { selectCategory(categories[it]) }
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(count = uiArticles.size) {
                ArticleItem(
                    image = uiArticles[it].urlToImage,
                    title = uiArticles[it].title,
                    publishedAt = uiArticles[it].publishedAt,
                    bookmarked = uiArticles[it].bookmarked,
                    onItemClick = onArticleClick
                )
            }
        }
    }
}

@Composable
fun EmptyTopHeadlines() {

}

@Composable
fun ArticlesDuringUpdate(
    search: String,
    onSearchChanged: (String) -> Unit,
    keyboardAction: KeyboardActionScope.() -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .systemBarsPadding()
            .padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        HeadlineAndDescriptionText(
            headline = stringResource(id = R.string.browse),
            description = stringResource(id = R.string.discover_things),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        SearchTextField(
            search = search,
            onSearchChange = { newSearch -> onSearchChanged(newSearch) },
            keyboardAction = keyboardAction,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 32.dp)
        )
        Box(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun TopHeadlinesWithError(
    search: String,
    onSearchChanged: (String) -> Unit,
    keyboardAction: KeyboardActionScope.() -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .systemBarsPadding()
            .padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        HeadlineAndDescriptionText(
            headline = stringResource(id = R.string.browse),
            description = stringResource(id = R.string.discover_things),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        SearchTextField(
            search = search,
            onSearchChange = { newSearch -> onSearchChanged(newSearch) },
            keyboardAction = keyboardAction,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 32.dp)
        )
        Box(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.error),
            contentAlignment = Alignment.Center
        ) {
            Text(text = stringResource(R.string.data_fetch_error), color = MaterialTheme.colorScheme.onError)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopHeadlinesScreenPreview(
    @PreviewParameter(ArticlesPreviewProvider::class, limit = 1) articles: List<UiArticle>
) {
    AggregateTheme {
        TopHeadlinesScreenContent(
            categories = listOf(
//                UiCategory(
//                    text = R.string.sports,
//                    selected = false
//                ),
//                UiCategory(
//                    text = R.string.sports,
//                    selected = true
//                ),
//                UiCategory(
//                    text = R.string.sports,
//                    selected = false
//                ),
            ),
            uiArticles = articles,
            search = "",
            onSearchChanged = {},
            selectCategory = {},
            keyboardAction = {},
            onArticleClick = {}
        )
    }
}

private class ArticlesPreviewProvider() : PreviewParameterProvider<UiArticle> {
    override val values: Sequence<UiArticle> = sequenceOf(
        UiArticle(),
        UiArticle(),
        UiArticle(),
    )
}