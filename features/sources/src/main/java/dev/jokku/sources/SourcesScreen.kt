package dev.jokku.sources

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import dev.jokku.designsystem.theme.AggregateTheme
import dev.jokku.ui.SourceItem

@androidx.compose.runtime.Composable
fun SourcesScreen(
    navController: NavHostController,
    viewModel: dev.jokku.aggregate.presentation.screens.sources.SourcesViewModel = hiltViewModel<dev.jokku.aggregate.presentation.screens.sources.MainSourcesViewModel>()
) {
    val state by viewModel.sourcesState.collectAsStateWithLifecycle()

    SourcesScreenContent(
        sources = state.sources
    )
}

@androidx.compose.runtime.Composable
fun SourcesScreenContent(
    sources: List<UiSource>
) {
    dev.jokku.ui.CommonColumn {
        dev.jokku.ui.HeadlineAndDescriptionText(
            headline = androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.categories),
            description = androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.thousands_articles)
        )
        LazyVerticalGrid(
            modifier = androidx.compose.ui.Modifier.padding(top = 32.dp),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            content = {
                items(count = sources.size) {
                    dev.jokku.ui.SourceItem(
                        country = sources[it].country,
                        name = sources[it].name,
                        onItemClick = { }
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@androidx.compose.runtime.Composable
fun SourcesScreenPreview() {
    dev.jokku.designsystem.theme.AggregateTheme {
        SourcesScreenContent(
            sources = listOf(
                UiSource(
                    name = "Source Name",
                    description = "Source's description",
                    url = "",
                    country = "Source Country"
                ),
                UiSource(
                    name = "Source Name",
                    description = "Source's description",
                    url = "",
                    country = "Source Country"
                )
            )
        )
    }
}