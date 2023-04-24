package com.jokku.aggregate.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jokku.aggregate.R
import com.jokku.aggregate.ui.viewmodel.Article
import com.jokku.aggregate.ui.viewmodel.MainNewsViewModel
import com.jokku.aggregate.ui.views.CommonColumn
import com.jokku.aggregate.ui.views.HeadlineAndDescriptionText

@Composable
fun BookmarksScreen(
    navController: NavHostController,
    viewModel: MainNewsViewModel = hiltViewModel()
) {
    val state = viewModel.bookmarksState.collectAsStateWithLifecycle().value

    CommonColumn {
        HeadlineAndDescriptionText(
            headline = R.string.bookmarks,
            description = R.string.saved_articles
        )
        if (state.bookmarkedArticles.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 96.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_empty_bookmarks_background),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_empty_bookmarks_foreground),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                Text(
                    text = stringResource(id = R.string.you_have_not_saved_any),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .width(256.dp)
                        .padding(top = 24.dp)
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(count = state.bookmarkedArticles.size) {
                    BookmarkedArticleItem(
                        article = state.bookmarkedArticles[it]
                    ) {

                    }
                }
            }
        }
    }
}

@Composable
fun BookmarkedArticleItem(
    article: Article,
    onItemClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp)
            .clickable(onClick = onItemClick),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(article.image)
                .size(96)
                .build(),
            contentDescription = article.title,
            modifier = Modifier.clip(MaterialTheme.shapes.medium),
            alignment = Alignment.CenterStart
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = article.sourceName,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSecondary
            )
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookmarkedArticleItemPreview() {
    BookmarkedArticleItem(
        article = Article(
            sourceName = "CNN",
            title = "Hundreds of Southwest Airlines flights are delayed after FAA lifts nationwide ground stop - CNN",
            image = R.drawable.img_news_mock_3
        )
    ) {

    }
}