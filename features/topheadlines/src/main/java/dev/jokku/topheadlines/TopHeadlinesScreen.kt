package dev.jokku.topheadlines

//noinspection SuspiciousImport
import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.jokku.designsystem.theme.AggregateTheme
import dev.jokku.ui.HeadlineAndDescriptionText
import dev.jokku.ui.SearchTextField
import dev.jokku.ui.UiArticle

@Composable
fun TopHeadlinesScreen(
    navController: NavHostController,
    viewModel: TopHeadlinesViewModel = hiltViewModel<MainTopHeadlinesViewModel>()
) { 
    val state by viewModel.topHeadlinesState.collectAsStateWithLifecycle()

    var search by rememberSaveable { mutableStateOf("") }
    
    TopHeadlinesScreenContent(
        categories = state.categories,
        uiArticles = state.articles,
        search = search,
        onSearchChanged = { newSearch -> search = newSearch},
        selectCategory = { category ->
            viewModel.selectCategory(category)
        },
        keyboardAction = {},
        onArticleClick = {}
    )
}

@Composable
fun TopHeadlinesScreenContent(
    categories: List<UiSelectableCategory>,
    uiArticles: List<UiArticle>,
    search: String,
    onSearchChanged: (String) -> Unit,
    selectCategory: (UiSelectableCategory) -> Unit,
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
                dev.jokku.ui.CategoryItem(
                    text = stringResource(id = categories[it].text),
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
                dev.jokku.ui.ArticleItem(
                    image = uiArticles[it].image,
                    title = uiArticles[it].title,
                    publishedAt = uiArticles[it].publishedAt,
                    bookmarked = uiArticles[it].bookmarked,
                    onItemClick = onArticleClick
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopHeadlinesScreenPreview() {
    AggregateTheme {
        TopHeadlinesScreenContent(
            categories = listOf(
                UiSelectableCategory(
                    text = R.string.sports,
                    selected = false
                ),
                UiSelectableCategory(
                    text = R.string.sports,
                    selected = true
                ),
                UiSelectableCategory(
                    text = R.string.sports,
                    selected = false
                ),
            ),
            uiArticles = listOf(
                UiArticle(),
                UiArticle()
            ),
            search = "",
            onSearchChanged = {},
            selectCategory = {},
            keyboardAction = {},
            onArticleClick = {}
        )
    }
}