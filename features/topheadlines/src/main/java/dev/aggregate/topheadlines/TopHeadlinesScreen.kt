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
import androidx.compose.material3.MaterialTheme.colorScheme
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
import dev.aggregate.designsystem.component.ArticleItem
import dev.aggregate.designsystem.component.CategoryItem
import dev.aggregate.designsystem.component.HeadlineAndDescriptionText
import dev.aggregate.designsystem.component.SearchTextField
import dev.aggregate.model.ui.UiArticle
import dev.aggregate.model.ui.UiCategory
import kotlinx.collections.immutable.ImmutableList

@Composable
fun TopHeadlinesScreen(
    navController: NavHostController,
    viewModel: TopHeadlinesViewModel = hiltViewModel<MainTopHeadlinesViewModel>(),
    modifier: Modifier = Modifier
) {
    val articlesState by viewModel.topHeadlinesState.collectAsStateWithLifecycle()
    val categoriesState by viewModel.categoriesState.collectAsStateWithLifecycle()

    var search by rememberSaveable { mutableStateOf("") }

    TopHeadlinesScreenInterface(
        categories = categoriesState.categories,
        state = articlesState,
        search = search,
        modifier = modifier,
        onSearchChanged = { newSearch -> search = newSearch },
        selectCategory = { category -> viewModel.selectCategory(category) },
        keyboardAction = {},
        onArticleClick = {}
    )
}

@Composable
fun TopHeadlinesScreenInterface(
    categories: List<UiCategory>,
    state: TopHeadlinesState,
    search: String,
    modifier: Modifier = Modifier,
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
                    text = categories[it].name.asString(),
                    selected = categories[it].selected,
                    onItemClick = { selectCategory(categories[it]) }
                )
            }
        }
        when (state) {
            is TopHeadlinesState.Error -> ErrorMessage(
                articles = state.articles,
                modifier = modifier,
                onArticleClick = onArticleClick
            )
            is TopHeadlinesState.InProgress -> ProgressIndicator(
                articles = state.articles,
                modifier = modifier,
                onArticleClick = onArticleClick
            )
            is TopHeadlinesState.None -> Unit
            is TopHeadlinesState.Success -> ArticlesList(
                articles = state.articles,
                modifier = modifier,
                onArticleClick = onArticleClick
            )
        }
    }
}

@Composable
fun ProgressIndicator(
    articles: ImmutableList<UiArticle>?,
    modifier: Modifier = Modifier,
    onArticleClick: () -> Unit
) {
    Column {
        Box(
            modifier
                .fillMaxWidth()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        if (articles != null) {
            ArticlesList(
                articles = articles,
                modifier = modifier,
                onArticleClick = onArticleClick
            )
        }
    }
}

@Composable
fun ErrorMessage(
    articles: ImmutableList<UiArticle>?,
    modifier: Modifier = Modifier,
    onArticleClick: () -> Unit
) {
    Column {
        Box(
            modifier
                .fillMaxWidth()
                .background(colorScheme.error)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.data_fetch_error),
                color = colorScheme.onError
            )
        }
        if (articles != null) {
            ArticlesList(
                articles = articles,
                modifier = modifier,
                onArticleClick = onArticleClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticlesList(
    @PreviewParameter(ArticlesPreviewProvider::class, limit = 1)
    articles: List<UiArticle>,
    modifier: Modifier = Modifier,
    onArticleClick: () -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(count = articles.size) {
            ArticleItem(
                image = articles[it].urlToImage,
                title = articles[it].title,
                publishedAt = articles[it].publishedAt,
                bookmarked = articles[it].bookmarked,
                onItemClick = onArticleClick
            )
        }
    }
}

private class ArticlesPreviewProvider : PreviewParameterProvider<UiArticle> {
    override val values: Sequence<UiArticle> = emptySequence()
}
