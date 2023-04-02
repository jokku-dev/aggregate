package com.jokku.aggregate.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jokku.aggregate.R
import com.jokku.aggregate.ui.data.GridItem
import com.jokku.aggregate.ui.nav.Screen
import com.jokku.aggregate.ui.viewmodel.WelcomeViewModel
import com.jokku.aggregate.ui.views.BigActionButton
import com.jokku.aggregate.ui.views.CommonColumn

@Composable
fun SelectFavoriteTopicsScreen(
    viewModel: WelcomeViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val topics = stringArrayResource(id = R.array.topics)

    CommonColumn {
        Text(
            modifier = Modifier.align(Alignment.Start),
            text = stringResource(id = R.string.select_your_favorite_topics),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            modifier = Modifier
                .padding(top = 8.dp)
                .align(Alignment.Start),
            text = stringResource(id = R.string.select_some_of_your_topics),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondary
        )
        LazyVerticalGrid(
            modifier = Modifier.padding(top = 32.dp),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            userScrollEnabled = false,
            content = {
                items(count = topics.size) { index ->
                    GridItem(index = index, topics = topics)
                }
            }
        )
        BigActionButton(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(id = R.string.next)
        ) {
            viewModel.setLaunchScreen(Screen.Home.route)
            navController.popBackStack()
            navController.navigate(Screen.Home.route)
        }
    }
}

@Composable
fun GridItem(
    index: Int,
    topics: Array<String>
) {
    var topicsState by rememberSaveable {
        mutableStateOf(
            (1..topics.size).map { i ->
                GridItem(topic = topics[i - 1], isSelected = false)
            }
        )
    }

    Box(
        modifier = Modifier
            .size(width = 160.dp, height = 72.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(
                color = if (topicsState[index].isSelected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.secondary
            )
            .clickable {
                topicsState = topicsState.mapIndexed { j, item ->
                    if (index == j) item.copy(isSelected = !item.isSelected)
                    else item
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = topicsState[index].topic,
            style = MaterialTheme.typography.titleLarge,
            color = if (topicsState[index].isSelected) MaterialTheme.colorScheme.surface
            else MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview
@Composable
fun SelectFavoriteTopicsScreenPreview() {
    SelectFavoriteTopicsScreen(navController = rememberNavController())
}