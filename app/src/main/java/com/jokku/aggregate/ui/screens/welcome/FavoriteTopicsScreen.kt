package com.jokku.aggregate.ui.screens.welcome

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
import com.jokku.aggregate.ui.entity.Topic
import com.jokku.aggregate.ui.nav.Screen
import com.jokku.aggregate.ui.theme.AggregateTheme
import com.jokku.aggregate.ui.viewmodel.WelcomeViewModel
import com.jokku.aggregate.ui.views.BigActionButton
import com.jokku.aggregate.ui.views.CommonColumn
import com.jokku.aggregate.ui.views.HeadlineAndDescriptionText
import com.jokku.aggregate.ui.views.TopicItem

@Composable
fun SelectFavoriteTopicsScreen(
    navController: NavHostController,
    viewModel: WelcomeViewModel = hiltViewModel()
) {
    val state by viewModel.favoriteTopicsState.collectAsStateWithLifecycle()

    SelectFavoriteTopicsScreenContent(
        topics = state.topics,
        changeIsTopicFavorite = { topic -> viewModel.changeIsTopicFavorite(topic) },
        onButtonClick = {
            viewModel.setFavoriteTopics(state.topics)
            viewModel.setLaunchScreen(Screen.Home.route)
            navController.popBackStack(route = Screen.SelectFavoriteTopics.route, inclusive = true)
            navController.navigate(route = Screen.Home.route)
        }
    )
    LaunchedEffect(key1 = LocalLifecycleOwner.current) {
        viewModel.getFavoriteTopics()
    }
}

@Composable
fun SelectFavoriteTopicsScreenContent(
    topics: List<Topic>,
    changeIsTopicFavorite: (Topic) -> Unit,
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
                items(count = topics.size) {
                    TopicItem(
                        text = stringResource(id = topics[it].text),
                        selected = topics[it].selected,
                        onItemClick = { changeIsTopicFavorite(topics[it]) }
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
            topics = listOf(
                Topic(text = R.string.sports_img, selected = true),
                Topic(text = R.string.sports_img, selected = false),
                Topic(text = R.string.sports_img, selected = false),
                Topic(text = R.string.sports_img, selected = true),
            ),
            changeIsTopicFavorite = {},
            onButtonClick = {}
        )
    }
}