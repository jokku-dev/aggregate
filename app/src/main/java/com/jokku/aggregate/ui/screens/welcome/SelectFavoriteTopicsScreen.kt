package com.jokku.aggregate.ui.screens.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jokku.aggregate.R
import com.jokku.aggregate.ui.entity.Topic
import com.jokku.aggregate.ui.nav.Screen
import com.jokku.aggregate.ui.viewmodel.WelcomeViewModel
import com.jokku.aggregate.ui.views.BigActionButton
import com.jokku.aggregate.ui.views.CommonColumn
import com.jokku.aggregate.ui.views.HeadlineAndDescriptionText

@Composable
fun SelectFavoriteTopicsScreen(
    navController: NavHostController,
    viewModel: WelcomeViewModel = hiltViewModel()
) {
    val topics = viewModel.favoriteTopicsState.collectAsStateWithLifecycle().value.topics

    CommonColumn {
        HeadlineAndDescriptionText(
            headline = R.string.select_your_favorite_topics,
            description = R.string.select_some_of_your_topics
        )
        LazyVerticalGrid(
            modifier = Modifier.padding(top = 32.dp),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            content = {
                items(count = topics.size) { index ->
                    TopicItem(topic = topics[index]) {
                        viewModel.changeIsTopicFavorite(changedTopic = topics[index])
                    }
                }
            }
        )
        BigActionButton(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(id = R.string.next)
        ) {
            viewModel.setFavoriteTopics(topics = topics)
            viewModel.setLaunchScreen(Screen.Home.route)
            navController.popBackStack()
            navController.navigate(Screen.Home.route)
        }
    }
    LaunchedEffect(key1 = LocalLifecycleOwner.current) {
        viewModel.getFavoriteTopics()
    }
}

@Composable
fun TopicItem(
    topic: Topic,
    onItemClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(width = 160.dp, height = 72.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(
                color = if (topic.selected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.secondary
            )
            .clickable(onClick = onItemClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = topic.text),
            style = MaterialTheme.typography.titleLarge,
            color = if (topic.selected) MaterialTheme.colorScheme.surface
            else MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview
@Composable
fun SelectFavoriteTopicsScreenPreview() {
    SelectFavoriteTopicsScreen(navController = rememberNavController())
}