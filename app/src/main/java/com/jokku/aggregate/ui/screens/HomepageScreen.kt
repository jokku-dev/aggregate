package com.jokku.aggregate.ui.screens

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.jokku.aggregate.R
import com.jokku.aggregate.ui.theme.AggregateTheme
import com.jokku.aggregate.ui.viewmodel.Article
import com.jokku.aggregate.ui.viewmodel.Category
import com.jokku.aggregate.ui.viewmodel.MainNewsViewModel
import com.jokku.aggregate.ui.views.ArticleItem
import com.jokku.aggregate.ui.views.CategoryItem
import com.jokku.aggregate.ui.views.HeadlineAndDescriptionText
import com.jokku.aggregate.ui.views.SearchTextField

@Composable
fun HomepageScreen(
    navController: NavHostController,
    viewModel: MainNewsViewModel = hiltViewModel()
) { 
    val state by viewModel.homeState.collectAsStateWithLifecycle()

    var search by rememberSaveable { mutableStateOf("") }
    
    HomepageScreenContent(
        categories = state.categories,
        articles = state.articles,
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
fun HomepageScreenContent(
    categories: List<Category>,
    articles: List<Article>,
    search: String,
    onSearchChanged: (String) -> Unit,
    selectCategory: (Category) -> Unit,
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
            items(count = articles.size) {
                ArticleItem(
                    image = articles[it].image,
                    title = articles[it].title,
                    publishedAt = articles[it].publishedAt,
                    bookmarked = articles[it].bookmarked,
                    onItemClick = onArticleClick
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AggregateTheme {
        HomepageScreenContent(
            categories = listOf(
                Category(
                    text = R.string.sports,
                    selected = false
                ),
                Category(
                    text = R.string.sports,
                    selected = true
                ),
                Category(
                    text = R.string.sports,
                    selected = false
                ),
            ),
            articles = listOf(
                Article(),
                Article()
            ),
            search = "",
            onSearchChanged = {},
            selectCategory = {},
            keyboardAction = {},
            onArticleClick = {}
        )
    }
}