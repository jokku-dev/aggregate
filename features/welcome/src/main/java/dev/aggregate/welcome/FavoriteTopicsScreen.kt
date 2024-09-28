package dev.aggregate.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import dev.aggregate.designsystem.Screen
import dev.aggregate.designsystem.component.BigActionButton
import dev.aggregate.designsystem.component.CommonColumn
import dev.aggregate.designsystem.component.HeadlineAndDescriptionText
import dev.aggregate.designsystem.component.TopicItem
import dev.aggregate.designsystem.theme.AggregateTheme
import dev.aggregate.model.ui.UiCategory
import dev.aggregate.model.ui.UiText

@Composable
fun SelectFavoriteTopicsScreen(
    navController: NavHostController,
    viewModel: WelcomeViewModel = hiltViewModel<MainWelcomeViewModel>()
) {
    val state by viewModel.favoriteCategoriesUiState.collectAsStateWithLifecycle()

    SelectFavoriteTopicsScreenContent(
        categories = state.categories,
        changeIsTopicFavorite = { topic -> /*viewModel.switchIsTopicFavorite(topic)*/ },
        onButtonClick = {
//            viewModel.setFavoriteTopics(state.categories)
            viewModel.setLaunchScreen(Screen.TopHeadlines.route)
            navController.popBackStack(route = Screen.SelectFavoriteTopics.route, inclusive = true)
            navController.navigate(route = Screen.TopHeadlines.route)
        }
    )
    LaunchedEffect(key1 = LocalLifecycleOwner.current) {
//        viewModel.getFavoriteTopics()
    }
}

@Composable
fun SelectFavoriteTopicsScreenContent(
    categories: List<UiCategory>,
    changeIsTopicFavorite: (UiCategory) -> Unit,
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
                        text = categories[it].name.asString(),
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
                UiCategory(name = UiText.StringResource(R.string.business), code = "", selected = true),
                UiCategory(name = UiText.StringResource(R.string.business), code = "", selected = false),
                UiCategory(name = UiText.StringResource(R.string.business), code = "", selected = false),
                UiCategory(name = UiText.StringResource(R.string.business), code = "", selected = true),
            ),
            changeIsTopicFavorite = {},
            onButtonClick = {}
        )
    }
}
