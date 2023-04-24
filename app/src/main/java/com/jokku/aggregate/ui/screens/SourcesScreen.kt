package com.jokku.aggregate.ui.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jokku.aggregate.R
import com.jokku.aggregate.ui.viewmodel.MainNewsViewModel
import com.jokku.aggregate.ui.viewmodel.Source
import com.jokku.aggregate.ui.views.CommonColumn
import com.jokku.aggregate.ui.views.HeadlineAndDescriptionText

@Composable
fun SourcesScreen(
    navController: NavHostController,
    viewModel: MainNewsViewModel = hiltViewModel()
) {
    val state = viewModel.sourceState.collectAsStateWithLifecycle().value

    CommonColumn {
        HeadlineAndDescriptionText(
            headline = R.string.categories,
            description = R.string.thousands_articles
        )
        LazyVerticalGrid(
            modifier = Modifier.padding(top = 32.dp),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            content = {
                items(count = state.sources.size) { index ->
                    SourceItem(source = state.sources[index]) {
                        
                    }
                }
            }
        )
    }
}

@Composable
fun SourceItem(
    source: Source,
    onItemClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(width = 160.dp, height = 72.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(color = MaterialTheme.colorScheme.surface)
            .clickable(onClick = onItemClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "${source.country} ${source.name}",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview
@Composable
fun SourcesScreenPreview() {
    SourcesScreen(navController = rememberNavController())
}