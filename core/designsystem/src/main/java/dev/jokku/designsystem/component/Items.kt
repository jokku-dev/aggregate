package dev.jokku.ui

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
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
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
import dev.jokku.aggregate.presentation.theme.AggregateTheme

@androidx.compose.runtime.Composable
fun CategoryItem(
    text: String,
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier,
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
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Text(
            text = text,
            style = typography.titleSmall,
            color = if (unchangeableTextColor) androidx.compose.ui.graphics.Color.White
            else if (selected) colorScheme.surface
            else colorScheme.onBackground
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@androidx.compose.runtime.Composable
fun ArticleItem(
    image: Int,
    title: String,
    publishedAt: String,
    bookmarked: Boolean,
    onItemClick: () -> Unit,
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier
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
            model = ImageRequest.Builder(androidx.compose.ui.platform.LocalContext.current)
                .data(image)
                .size(Size.ORIGINAL)
                .build(),
            contentDescription = title,
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
            contentScale = androidx.compose.ui.layout.ContentScale.Fit
        )
        Column(
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = typography.titleSmall,
                color = colorScheme.onSurfaceVariant,
                maxLines = 3,
                overflow = androidx.compose.ui.text.style.TextOverflow.Clip,
            )
            Row(
                modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                Text(
                    text = publishedAt,
                    style = typography.titleSmall,
                    color = colorScheme.onSecondary
                )
                Icon(
                    imageVector = if (bookmarked) androidx.compose.ui.graphics.vector.ImageVector.vectorResource(
                        dev.jokku.aggregate.R.drawable.ic_bookmark_selected
                    )
                    else androidx.compose.ui.graphics.vector.ImageVector.vectorResource(dev.jokku.aggregate.R.drawable.ic_bookmark),
                    contentDescription = if (bookmarked) androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.bookmarked)
                    else androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.not_bookmarked)
                )
            }
        }
    }
}

@androidx.compose.runtime.Composable
fun SourceItem(
    country: String,
    name: String,
    onItemClick: () -> Unit
) {
    Box(
        modifier = androidx.compose.ui.Modifier
            .size(width = 160.dp, height = 72.dp)
            .background(color = colorScheme.surface)
            .border(
                border = BorderStroke(width = 1.dp, color = colorScheme.secondary),
                shape = shapes.medium
            )
            .clickable(onClick = onItemClick),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Text(
            text = "$country $name",
            style = typography.titleLarge,
            color = colorScheme.onBackground
        )
    }
}

@androidx.compose.runtime.Composable
fun BookmarkedArticleItem(
    urlToImage: String,
    title: String,
    sourceName: String,
    onItemClick: () -> Unit
) {
    Row(
        modifier = androidx.compose.ui.Modifier
            .fillMaxWidth()
            .height(96.dp)
            .clickable(onClick = onItemClick),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
//        AsyncImage(
//            model = ImageRequest.Builder(LocalContext.current)
//                .data(urlToImage)
//                .size(96)
//                .build(),
//            contentDescription = article.title,
//            modifier = Modifier.clip(MaterialTheme.shapes.medium),
//            alignment = Alignment.CenterStart
//        )
        Image(
            painter = androidx.compose.ui.res.painterResource(id = dev.jokku.aggregate.R.drawable.img_news_mock_1),
            contentDescription = title
        )
        Column(
            modifier = androidx.compose.ui.Modifier
                .fillMaxHeight()
                .padding(start = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = androidx.compose.ui.Alignment.Start
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
                overflow = androidx.compose.ui.text.style.TextOverflow.Clip
            )
        }
    }
}

@androidx.compose.runtime.Composable
fun TopicItem(
    text: String,
    selected: Boolean,
    onItemClick: () -> Unit
) {
    Box(
        modifier = androidx.compose.ui.Modifier
            .size(width = 160.dp, height = 72.dp)
            .clip(shapes.medium)
            .background(color = if (selected) colorScheme.primary else colorScheme.secondary)
            .clickable(onClick = onItemClick),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Text(
            text = text,
            style = typography.titleLarge,
            color = if (selected) colorScheme.surface else colorScheme.onBackground
        )
    }
}

@Preview
@androidx.compose.runtime.Composable
fun CategoryItemPreview() {
    dev.jokku.aggregate.presentation.theme.AggregateTheme {
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
@androidx.compose.runtime.Composable
fun ArticleItemPreview() {
    dev.jokku.aggregate.presentation.theme.AggregateTheme {
        ArticleItem(
            image = dev.jokku.aggregate.R.drawable.img_news_mock_4,
            title = "Article title title title title title title title title title title title" +
                    " title title title title title title title title title title title title",
            publishedAt = "2023.01.01",
            bookmarked = true,
            onItemClick = {}
        )
    }
}

@Preview
@androidx.compose.runtime.Composable
fun SourceItemPreview() {
    dev.jokku.aggregate.presentation.theme.AggregateTheme {
        SourceItem(
            country = "Country",
            name = "Name",
            onItemClick = {}
        )
    }
}

@Preview(showBackground = true)
@androidx.compose.runtime.Composable
fun BookmarkedArticleItemPreview() {
    dev.jokku.aggregate.presentation.theme.AggregateTheme {
        BookmarkedArticleItem(
            urlToImage = "",
            title = "Fox News' sudden firing of Tucker Carlson may have come down to one simple calculation - CNN",
            sourceName = "CNN",
            onItemClick = {}
        )
    }
}

@Preview
@androidx.compose.runtime.Composable
fun TopicItemPreview() {
    dev.jokku.aggregate.presentation.theme.AggregateTheme {
        Column {
            TopicItem(text = "Topic name", selected = true, onItemClick = {})
            TopicItem(text = "Topic name", selected = false, onItemClick = {})
        }
    }
}