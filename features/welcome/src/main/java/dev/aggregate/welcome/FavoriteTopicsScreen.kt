package dev.aggregate.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import dev.aggregate.designsystem.theme.AggregateTheme
import dev.aggregate.navigation.Screen
import dev.aggregate.ui.TopicItem

@androidx.compose.runtime.Composable
fun SelectFavoriteTopicsScreen(
    navController: NavHostController,
    viewModel: dev.aggregate.app.presentation.screens.welcome.MainWelcomeViewModel = hiltViewModel()
) {
    val state by viewModel.favoriteCategoriesUiState.collectAsStateWithLifecycle()

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
    androidx.compose.runtime.LaunchedEffect(key1 = androidx.compose.ui.platform.LocalLifecycleOwner.current) {
        viewModel.getFavoriteTopics()
    }
}

@androidx.compose.runtime.Composable
fun SelectFavoriteTopicsScreenContent(
    categories: List<UiSelectableCategory>,
    changeIsTopicFavorite: (UiSelectableCategory) -> Unit,
    onButtonClick: () -> Unit
) {
    dev.aggregate.app.ui.CommonColumn {
        dev.aggregate.app.ui.HeadlineAndDescriptionText(
            headline = androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.select_your_favorite_topics),
            description = androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.select_some_of_your_topics)
        )
        LazyVerticalGrid(
            modifier = androidx.compose.ui.Modifier
                .padding(top = 32.dp)
                .weight(1f), // need for a lazy column to not let take up all the screen place
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            content = {
                items(count = categories.size) {
                    dev.aggregate.app.ui.TopicItem(
                        text = androidx.compose.ui.res.stringResource(id = categories[it].text),
                        selected = categories[it].selected,
                        onItemClick = { changeIsTopicFavorite(categories[it]) }
                    )
                }
            }
        )
        dev.aggregate.app.ui.BigActionButton(
            modifier = androidx.compose.ui.Modifier.padding(vertical = 16.dp),
            text = androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.next),
            onClick = onButtonClick
        )
    }
}


@Preview(showBackground = true)
@androidx.compose.runtime.Composable
fun SelectFavoriteTopicsScreenPreview() {
    dev.aggregate.app.designsystem.theme.AggregateTheme {
        SelectFavoriteTopicsScreenContent(
            categories = listOf(
                UiSelectableCategory(text = dev.aggregate.app.R.string.business, selected = true),
                UiSelectableCategory(text = dev.aggregate.app.R.string.business, selected = false),
                UiSelectableCategory(text = dev.aggregate.app.R.string.business, selected = false),
                UiSelectableCategory(text = dev.aggregate.app.R.string.business, selected = true),
            ),
            changeIsTopicFavorite = {},
            onButtonClick = {}
        )
    }
}