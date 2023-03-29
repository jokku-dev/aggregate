package com.jokku.aggregate.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jokku.aggregate.R
import com.jokku.aggregate.ui.nav.Screen
import com.jokku.aggregate.ui.views.BigActionButton

@Composable
fun SelectFavoriteTopicsScreen(
    navController: NavHostController
) {

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .align(Alignment.Start),
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
                    items(count = 10) { item ->
                        Box(
                            modifier = Modifier
                                .size(width = 160.dp, height = 72.dp)
                                .clip(MaterialTheme.shapes.medium)
                                .background(color = MaterialTheme.colorScheme.secondary),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringArrayResource(id = R.array.topics)[item],
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                }
            )
            BigActionButton(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(id = R.string.next)
            ) {
                navController.popBackStack()
                navController.navigate(route = Screen.Home.route)
            }
        }
    }
}

@Preview
@Composable
fun SelectFavoriteTopicsScreenPreview() {
    SelectFavoriteTopicsScreen(navController = rememberNavController())
}