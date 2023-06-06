package com.jokku.aggregate.presentation.screens.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.jokku.aggregate.R
import com.jokku.aggregate.presentation.nav.Screen
import com.jokku.aggregate.presentation.theme.AggregateTheme
import com.jokku.aggregate.presentation.views.BigActionButton
import com.jokku.aggregate.presentation.views.CommonColumn
import com.jokku.aggregate.presentation.views.HeadlineAndDescriptionText
import com.jokku.aggregate.presentation.views.TopicItem

@Composable
fun SelectFavoriteTopicsScreen(
    navController: NavHostController,
    viewModel: MainWelcomeViewModel = hiltViewModel()
) {
    val state by viewModel.favoriteCategoriesState.collectAsStateWithLifecycle()

    SelectFavoriteTopicsScreenContent(
        categories = state.categories,
        changeIsTopicFavorite = { topic -> viewModel.switchIsTopicFavorite(topic) },
        onButtonClick = {
            viewModel.setFavoriteTopics(state.categories)
            viewModel.setLaunchScreen(Screen.TopHeadlines.route)
            navController.popBackStack(route = Screen.SelectFavoriteTopics.route, inclusive = true)
            navController.navigate(route = Screen.TopHeadlines.route)
        }
    )
    LaunchedEffect(key1 = LocalLifecycleOwner.current) {
        viewModel.getFavoriteTopics()
    }
}

@Composable
fun SelectFavoriteTopicsScreenContent(
    categories: List<UiSelectableCategory>,
    changeIsTopicFavorite: (UiSelectableCategory) -> Unit,
    onButtonClick: () -> Unit
) {
    CommonColumn {
        HeadlineAndDescriptionText(
            headline = stringResource(id = R.string.select_your_favorite_topics),
            description = stringResource(id = R.string.select_some_of_your_topics)
        )
        LazyVerticalGrid(
            modifier = Modifier
                .padding(top = 32.dp)
                .weight(1f), // need for a lazy column to not let take up all the screen place
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            content = {
                items(count = categories.size) {
                    TopicItem(
                        text = stringResource(id = categories[it].text),
                        selected = categories[it].selected,
                        onItemClick = { changeIsTopicFavorite(categories[it]) }
                    )
                }
            }
        )
        BigActionButton(
            modifier = Modifier.padding(vertical = 16.dp),
            text = stringResource(id = R.string.next),
            onClick = onButtonClick
        )
    }
}


@Preview(showBackground = true)
@Composable
fun SelectFavoriteTopicsScreenPreview() {
    AggregateTheme {
        SelectFavoriteTopicsScreenContent(
            categories = listOf(
                UiSelectableCategory(text = R.string.business, selected = true),
                UiSelectableCategory(text = R.string.business, selected = false),
                UiSelectableCategory(text = R.string.business, selected = false),
                UiSelectableCategory(text = R.string.business, selected = true),
            ),
            changeIsTopicFavorite = {},
            onButtonClick = {}
        )
    }
}