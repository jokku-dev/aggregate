package com.jokku.aggregate.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.jokku.aggregate.R
import com.jokku.aggregate.ui.viewmodel.ArticlePreview
import com.jokku.aggregate.ui.viewmodel.Category
import com.jokku.aggregate.ui.viewmodel.HomeViewModel
import com.jokku.aggregate.ui.views.HeadlineAndDescription
import com.jokku.aggregate.ui.views.SearchTextField

@Composable
fun HomepageScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    var search by rememberSaveable { mutableStateOf("") }
    val state = viewModel.homeState.collectAsStateWithLifecycle().value
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        HeadlineAndDescription(
            headline = R.string.browse,
            description = R.string.discover_things,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        SearchTextField(
            search = search,
            onSearchChange = { newSearch -> search = newSearch },
            keyboardAction = {  },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 32.dp)
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 24.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(count = state.categories.size) { index ->
                RowCategoryItem(
                    category = state.categories[index]
                ) {
                    viewModel.selectCategory(state.categories[index])
                }
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(count = state.news.size) {
                NewsArticleItem(
                    preview = state.news[it],
                    bookmark = if (state.news[it].bookmarked) {
                        ImageVector.vectorResource(id = R.drawable.ic_outline_bookmark_selected)
                    } else {
                        ImageVector.vectorResource(id = R.drawable.ic_outline_bookmark)
                    }
                ) {

                }
            }
        }
    }

}

@Composable
fun RowCategoryItem(
    category: Category,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit
) {
    Box(
        modifier = modifier
            .width(IntrinsicSize.Min)
            .height(32.dp)
            .clip(MaterialTheme.shapes.large)
            .background(
                color = if (category.selected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.secondary
            )
            .clickable(onClick = onItemClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = stringResource(id = category.text),
            style = MaterialTheme.typography.titleSmall,
            color = if (category.selected) MaterialTheme.colorScheme.surface
            else MaterialTheme.colorScheme.onBackground
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsArticleItem(
    preview: ArticlePreview,
    bookmark: ImageVector,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit
) {
    OutlinedCard(
        onClick = onItemClick,
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp),
        shape = CardDefaults.outlinedShape,
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.secondary)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(preview.image)
                .size(Size.ORIGINAL)
                .build(),
            contentDescription = preview.title,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
            contentScale = ContentScale.Fit
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = preview.title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 3,
                overflow = TextOverflow.Clip
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = preview.publishedAt,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSecondary
                )
                Icon(
                    imageVector = bookmark,
                    contentDescription = if (preview.bookmarked) stringResource(id = R.string.bookmarked)
                    else stringResource(id = R.string.not_bookmarked)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HomepageScreen(navController = rememberNavController())
}

@Preview(showBackground = true, wallpaper = Wallpapers.NONE)
@Composable
fun NewsArticleItemPreview() {
    NewsArticleItem(
        preview = ArticlePreview(
            image = R.drawable.img_news_mock_6,
            title = "Following Weekend of ‘Reckless, Disruptive' Gatherings Downtown, Some Call For Teen Curfew to Return - NBC Chicago",
            publishedAt = "01.01.2023",
            bookmarked = false
        ),
        bookmark = ImageVector.vectorResource(id = R.drawable.ic_outline_bookmark_selected)
    ) {

    }
}