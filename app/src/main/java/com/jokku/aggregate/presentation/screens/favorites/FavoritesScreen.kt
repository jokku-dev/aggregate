package com.jokku.aggregate.presentation.screens.favorites

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
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
import com.jokku.aggregate.R
import com.jokku.aggregate.presentation.entity.UiSelectableCategory
import com.jokku.aggregate.presentation.model.UiArticle
import com.jokku.aggregate.presentation.theme.AggregateTheme
import com.jokku.aggregate.presentation.views.HeadlineAndDescriptionText

@Composable
fun FavoritesScreen(
    navController: NavHostController,
    viewModel: FavoritesViewModel = hiltViewModel<DefaultFavoritesViewModel>()
) {
    val state by viewModel.favoritesUiState.collectAsStateWithLifecycle()

    FavoritesScreenContent(
        categorisedArticles = (state as? FavoritesState.HasArticles)?.categorisedArticles ?: emptyList()
    )
}

@Composable
fun FavoritesScreenContent(
    categorisedArticles: List<UiCategoryArticles>,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = 24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        HeadlineAndDescriptionText(
            headline = stringResource(id = R.string.favorites),
            description = stringResource(id = R.string.categories_for_your_taste),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 32.dp)
                .verticalScroll(state = scrollState)
        ) {
            repeat(categorisedArticles.size) {
                CategoryNewsBlock(
                    category = stringResource(id = categorisedArticles[it].category.text),
                    articles = categorisedArticles[it].articles,
                    onArticleClick = { }
                )
            }
        }
    }
}

@Composable
fun CategoryNewsBlock(
    category: String,
    articles: List<UiArticle>,
    onArticleClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()

    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = category,
            style = typography.titleLarge,
            color = colorScheme.onSurfaceVariant
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
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

@Composable
fun ArticleCard(
    urlToImage: String,
    bookmarked: Boolean,
    source: String,
    title: String,
    onArticleClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(256.dp)
            .clip(shapes.medium)
            .clickable { onArticleClick() },
    ) {
//        AsyncImage(model = urlToImage, contentDescription = title)
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.img_news_mock_1),
            contentDescription = title,
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply {
                setToScale(
                    0.5f,
                    0.5f,
                    0.5f,
                    1f
                )
            }),
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.size(24.dp))
                Icon(
                    imageVector = if (bookmarked) ImageVector.vectorResource(R.drawable.ic_bookmark_selected)
                    else ImageVector.vectorResource(R.drawable.ic_bookmark),
                    contentDescription = if (bookmarked) stringResource(id = R.string.bookmarked)
                    else stringResource(id = R.string.not_bookmarked),
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
@Composable
fun FavoritesScreenPreview() {
    AggregateTheme {
        FavoritesScreenContent(
            categorisedArticles = listOf(
                UiCategoryArticles(
                    articles = listOf(
                        UiArticle(),
                        UiArticle(),
                        UiArticle()
                    ),
                    category = UiSelectableCategory(text = R.string.sports)
                )
            )
        )
    }
}

@Preview
@Composable
fun ArticleCardPreview() {
    AggregateTheme {
        ArticleCard(
            urlToImage = "",
            bookmarked = false,
            source = "THE NEW YORK TIMES",
            title = "Title title title title title title title title title title title title title",
            onArticleClick = {}
        )
    }
}