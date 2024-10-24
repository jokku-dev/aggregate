package dev.aggregate.sources

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import dev.aggregate.designsystem.component.CommonColumn
import dev.aggregate.designsystem.component.HeadlineAndDescriptionText
import dev.aggregate.designsystem.component.SourceItem
import dev.aggregate.designsystem.theme.AggregateTheme
import dev.aggregate.model.ui.UiNewsSource

@Composable
fun SourcesScreen(
    navController: NavHostController,
    viewModel: SourcesViewModel = hiltViewModel<MainSourcesViewModel>()
) {
    val state by viewModel.sourcesState.collectAsStateWithLifecycle()

    SourcesScreenContent(
        sources = state.sources
    )
}

@Composable
fun SourcesScreenContent(
    sources: List<UiNewsSource>
) {
    CommonColumn {
        HeadlineAndDescriptionText(
            headline = stringResource(id = R.string.categories),
            description = stringResource(id = R.string.thousands_articles)
        )
        LazyVerticalGrid(
            modifier = Modifier.padding(top = 32.dp),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            content = {
                items(count = sources.size) {
                    SourceItem(
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
@Composable
fun SourcesScreenPreview() {
    AggregateTheme {
        SourcesScreenContent(
            sources = listOf(
                UiNewsSource(
                    name = "Source Name",
                    description = "Source's description",
                    url = "",
                    country = "Source Country"
                ),
                UiNewsSource(
                    name = "Source Name",
                    description = "Source's description",
                    url = "",
                    country = "Source Country"
                )
            )
        )
    }
}
