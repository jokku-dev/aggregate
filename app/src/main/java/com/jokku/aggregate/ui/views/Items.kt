package com.jokku.aggregate.ui.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.jokku.aggregate.R
import com.jokku.aggregate.ui.theme.AggregateTheme

@Composable
fun CategoryItem(
    text: String,
    modifier: Modifier = Modifier,
    unchangeableTextColor: Boolean = false,
    selected: Boolean = true,
    onItemClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .height(32.dp)
            .clip(shapes.large)
            .background(color = if (selected) colorScheme.primary else colorScheme.secondary)
            .padding(horizontal = 16.dp)
            .clickable(onClick = onItemClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = typography.titleSmall,
            color = if (unchangeableTextColor) Color.White
            else if (selected) colorScheme.surface
            else colorScheme.onBackground
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleItem(
    image: Int,
    title: String,
    publishedAt: String,
    bookmarked: Boolean,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        onClick = onItemClick,
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp),
        shape = CardDefaults.outlinedShape,
        colors = CardDefaults.outlinedCardColors(containerColor = colorScheme.surface),
        border = BorderStroke(width = 1.dp, color = colorScheme.secondary)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(image)
                .size(Size.ORIGINAL)
                .build(),
            contentDescription = title,
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
                text = title,
                style = typography.titleSmall,
                color = colorScheme.onSurfaceVariant,
                maxLines = 3,
                overflow = TextOverflow.Clip,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = publishedAt,
                    style = typography.titleSmall,
                    color = colorScheme.onSecondary
                )
                Icon(
                    imageVector = if (bookmarked) ImageVector.vectorResource(R.drawable.ic_bookmark_selected)
                    else ImageVector.vectorResource(R.drawable.ic_bookmark),
                    contentDescription = if (bookmarked) stringResource(id = R.string.bookmarked)
                    else stringResource(id = R.string.not_bookmarked)
                )
            }
        }
    }
}

@Composable
fun SourceItem(
    country: String,
    name: String,
    onItemClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(width = 160.dp, height = 72.dp)
            .background(color = colorScheme.surface)
            .border(
                border = BorderStroke(width = 1.dp, color = colorScheme.secondary),
                shape = shapes.medium
            )
            .clickable(onClick = onItemClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$country $name",
            style = typography.titleLarge,
            color = colorScheme.onBackground
        )
    }
}

@Composable
fun BookmarkedArticleItem(
    image: Int,
    title: String,
    sourceName: String,
    onItemClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp)
            .clickable(onClick = onItemClick),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
//        AsyncImage(
//            model = ImageRequest.Builder(LocalContext.current)
//                .data(article.image)
//                .size(96)
//                .build(),
//            contentDescription = article.title,
//            modifier = Modifier.clip(MaterialTheme.shapes.medium),
//            alignment = Alignment.CenterStart
//        )
        Image(
            painter = painterResource(id = image),
            contentDescription = title
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = sourceName,
                style = typography.labelMedium,
                color = colorScheme.onSecondary
            )
            Text(
                text = title,
                style = typography.titleSmall,
                color = colorScheme.onSurfaceVariant,
                maxLines = 3,
                overflow = TextOverflow.Clip
            )
        }
    }
}

@Composable
fun TopicItem(
    text: String,
    selected: Boolean,
    onItemClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(width = 160.dp, height = 72.dp)
            .clip(shapes.medium)
            .background(color = if (selected) colorScheme.primary else colorScheme.secondary)
            .clickable(onClick = onItemClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = typography.titleLarge,
            color = if (selected) colorScheme.surface else colorScheme.onBackground
        )
    }
}

@Preview
@Composable
fun CategoryItemPreview() {
    AggregateTheme {
        Column {
            CategoryItem(
                text = "Category"
            )
            CategoryItem(
                text = "Category",
                selected = false
            )
        }
    }
}

@Preview
@Composable
fun ArticleItemPreview() {
    AggregateTheme {
        ArticleItem(
            image = R.drawable.img_news_mock_4,
            title = "Article title title title title title title title title title title title" +
                    " title title title title title title title title title title title title",
            publishedAt = "2023.01.01",
            bookmarked = true,
            onItemClick = {}
        )
    }
}

@Preview
@Composable
fun SourceItemPreview() {
    AggregateTheme {
        SourceItem(
            country = "Country",
            name = "Name",
            onItemClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BookmarkedArticleItemPreview() {
    AggregateTheme {
        BookmarkedArticleItem(
            image = R.drawable.img_news_mock_1,
            title = "Fox News' sudden firing of Tucker Carlson may have come down to one simple calculation - CNN",
            sourceName = "CNN",
            onItemClick = {}
        )
    }
}

@Preview
@Composable
fun TopicItemPreview() {
    AggregateTheme {
        Column {
            TopicItem(text = "Topic name", selected = true, onItemClick = {})
            TopicItem(text = "Topic name", selected = false, onItemClick = {})
        }
    }
}