package dev.jokku.favorites

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import dev.jokku.aggregate.presentation.entity.UiSelectableCategory
import dev.jokku.aggregate.presentation.model.UiArticle
import dev.jokku.designsystem.theme.AggregateTheme
import kotlin.apply
import kotlin.text.category

@androidx.compose.runtime.Composable
fun FavoritesScreen(
    navController: NavHostController,
    viewModel: dev.jokku.aggregate.presentation.screens.favorites.FavoritesViewModel = hiltViewModel<dev.jokku.aggregate.presentation.screens.favorites.DefaultFavoritesViewModel>()
) {
    val state by viewModel.favoritesUiState.collectAsStateWithLifecycle()

    FavoritesScreenContent(
        categorisedArticles = (state as? dev.jokku.aggregate.presentation.screens.favorites.FavoritesState.HasArticles)?.categorisedArticles ?: emptyList()
    )
}

@androidx.compose.runtime.Composable
fun FavoritesScreenContent(
    categorisedArticles: List<UiCategoryArticles>,
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = 24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = androidx.compose.ui.Alignment.Start
    ) {
        dev.jokku.ui.HeadlineAndDescriptionText(
            headline = androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.favorites),
            description = androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.categories_for_your_taste),
            modifier = androidx.compose.ui.Modifier.padding(horizontal = 16.dp)
        )
        Column(
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .padding(top = 32.dp)
                .verticalScroll(state = scrollState)
        ) {
            repeat(categorisedArticles.size) {
                CategoryNewsBlock(
                    category = androidx.compose.ui.res.stringResource(id = categorisedArticles[it].category.text),
                    articles = categorisedArticles[it].articles,
                    onArticleClick = { }
                )
            }
        }
    }
}

@androidx.compose.runtime.Composable
fun CategoryNewsBlock(
    category: String,
    articles: List<dev.jokku.aggregate.presentation.model.UiArticle>,
    onArticleClick: () -> Unit,
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier
) {
    val listState = rememberLazyListState()

    Column(
        modifier = modifier
    ) {
        Text(
            modifier = androidx.compose.ui.Modifier.padding(horizontal = 16.dp),
            text = category,
            style = typography.titleLarge,
            color = colorScheme.onSurfaceVariant
        )
        LazyRow(
            modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
            state = listState,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
        ) {
            items(count = articles.size) {
                ArticleCard(
                    urlToImage = articles[it].urlToImage,
                    bookmarked = articles[it].bookmarked,
                    source = articles[it].source.name,
                    title = articles[it].title,
                    onArticleClick = onArticleClick
                )
            }
        }

    }
}

@androidx.compose.runtime.Composable
fun ArticleCard(
    urlToImage: String,
    bookmarked: Boolean,
    source: String,
    title: String,
    onArticleClick: () -> Unit,
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier
) {
    Box(
        modifier = modifier
            .size(256.dp)
            .clip(shapes.medium)
            .clickable { onArticleClick() },
    ) {
//        AsyncImage(model = urlToImage, contentDescription = title)
        Image(
            modifier = androidx.compose.ui.Modifier.fillMaxSize(),
            painter = androidx.compose.ui.res.painterResource(id = dev.jokku.aggregate.R.drawable.img_news_mock_1),
            contentDescription = title,
            contentScale = androidx.compose.ui.layout.ContentScale.Crop,
            colorFilter = androidx.compose.ui.graphics.ColorFilter.colorMatrix(
                androidx.compose.ui.graphics.ColorMatrix().apply {
                setToScale(
                    0.5f,
                    0.5f,
                    0.5f,
                    1f
                )
            }),
        )
        Column(
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = androidx.compose.ui.Modifier.size(24.dp))
                Icon(
                    imageVector = if (bookmarked) androidx.compose.ui.graphics.vector.ImageVector.vectorResource(
                        dev.jokku.aggregate.R.drawable.ic_bookmark_selected
                    )
                    else androidx.compose.ui.graphics.vector.ImageVector.vectorResource(dev.jokku.aggregate.R.drawable.ic_bookmark),
                    contentDescription = if (bookmarked) androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.bookmarked)
                    else androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.not_bookmarked),
                    tint = colorScheme.surface
                )
            }
            Column {
                Text(
                    text = source,
                    style = typography.labelSmall,
                    color = colorScheme.secondary
                )
                Text(
                    text = title,
                    style = typography.titleLarge,
                    color = colorScheme.surface
                )
            }
        }
    }
}

@Preview(showBackground = true)
@androidx.compose.runtime.Composable
fun FavoritesScreenPreview() {
    dev.jokku.designsystem.theme.AggregateTheme {
        FavoritesScreenContent(
            categorisedArticles = listOf(
                UiCategoryArticles(
                    articles = listOf(
                        dev.jokku.aggregate.presentation.model.UiArticle(),
                        dev.jokku.aggregate.presentation.model.UiArticle(),
                        dev.jokku.aggregate.presentation.model.UiArticle()
                    ),
                    category = UiSelectableCategory(text = dev.jokku.aggregate.R.string.sports)
                )
            )
        )
    }
}

@Preview
@androidx.compose.runtime.Composable
fun ArticleCardPreview() {
    dev.jokku.designsystem.theme.AggregateTheme {
        ArticleCard(
            urlToImage = "",
            bookmarked = false,
            source = "THE NEW YORK TIMES",
            title = "Title title title title title title title title title title title title title",
            onArticleClick = {}
        )
    }
}