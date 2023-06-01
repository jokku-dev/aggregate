package com.jokku.aggregate.presentation.screens.article

import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jokku.aggregate.R
import com.jokku.aggregate.presentation.model.UiArticle
import com.jokku.aggregate.presentation.model.UiArticleSource
import com.jokku.aggregate.presentation.nav.Screen
import com.jokku.aggregate.presentation.screens.BaseNewsViewModel
import com.jokku.aggregate.presentation.screens.NewsViewModel
import com.jokku.aggregate.presentation.theme.AggregateTheme
import com.jokku.aggregate.presentation.theme.GreyLight
import com.jokku.aggregate.presentation.views.ArticleTopBar
import com.jokku.aggregate.presentation.views.CategoryItem

private const val OTHER = "other"

@Composable
fun ArticleScreen(
    navController: NavHostController,
    viewModel: NewsViewModel = hiltViewModel<BaseNewsViewModel>(),
    systemUiController: SystemUiController = rememberSystemUiController(),
    colorScheme: ColorScheme = MaterialTheme.colorScheme
) {
    val state by viewModel.articleState.collectAsStateWithLifecycle()
    var currentScreen by remember { mutableStateOf(Screen.ArticleScreen.route) }
    
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val lifecycleOwner = LocalLifecycleOwner.current

    ArticleScreenContent(
        article = state.article,
        onBackClick = {
            currentScreen = OTHER
            navController.popBackStack()
        }
    )

    LaunchedEffect(key1 = currentScreen) {
        if (currentScreen == Screen.ArticleScreen.route) {
            systemUiController.setStatusBarColor(color = Color.Transparent)
        } else {
            systemUiController.setStatusBarColor(color = colorScheme.surface)
        }
    }
    // On back pressed handling to bring back the color of the status bar
    DisposableEffect(key1 = Unit) {
        val backCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                currentScreen = OTHER
                isEnabled = false
                navController.popBackStack()
            }
        }
        onBackPressedDispatcher?.addCallback(
            owner = lifecycleOwner,
            onBackPressedCallback = backCallback
        )
        onDispose { backCallback.remove() }
    }
}

@Composable
fun ArticleScreenContent(
    article: UiArticle,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    headerHeight: Dp = 400.dp,
    heightToShowTopBar: Dp = 108.dp,
    topBarsHeight: Dp = 84.dp,
    overlayPadding: Dp = 32.dp
) {
    val scrollState = rememberScrollState()
    // Density scope provides conversion to pixels
    val headerHeightPx = with(LocalDensity.current) { headerHeight.toPx() }
    val topBarHeightPx = with(LocalDensity.current) { heightToShowTopBar.toPx() }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter
    ) {
        ArticleHeader(
            urlToImage = article.urlToImage,
            title = article.title,
            sourceName = article.source.name,
            author = article.author,
            bookmarked = article.bookmarked,
            scrollState = scrollState,
            headerHeightPx = headerHeightPx,
            onBackClick = onBackClick,
            onShareClick = {},
            onBookmarkClick = {},
            modifier = Modifier.height(headerHeight)
        )
        ArticleBody(
            scrollState = scrollState,
            headerHeight = headerHeight,
            topBarsHeight = topBarsHeight,
            overlayPadding = overlayPadding
        )
        ArticleTopBar(
            bookmarked = article.bookmarked,
            sourceName = article.source.name,
            url = article.url,
            topBarHeightPx = topBarHeightPx,
            headerHeightPx = headerHeightPx,
            scrollState = scrollState,
            onBackClick = onBackClick,
            onShareClick = {},
            onBookmarkClick = {},
        )
    }
}

@Composable
fun ArticleHeader(
    urlToImage: String,
    title: String,
    sourceName: String,
    author: String,
    bookmarked: Boolean,
    scrollState: ScrollState,
    headerHeightPx: Float,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    onBookmarkClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            // lambda version of graphicsLayer to avoid unnecessary recompositions
            // which induce bad performance
            .graphicsLayer {
                // parallax effect
                translationY = -scrollState.value.toFloat() / 2f
                // fade animation using affine function
                alpha = -1f / headerHeightPx * scrollState.value + 1
            },
    ) {
//        AsyncImage(model = urlToImage, contentDescription =)
        Image(
            painter = painterResource(id = R.drawable.img_news_mock_1),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply {
                setToScale(
                    0.5f,
                    0.5f,
                    0.5f,
                    1f
                )
            }),
            modifier = Modifier.fillMaxSize(),
        )
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(top = 36.dp, bottom = 32.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_back),
                        contentDescription = stringResource(id = R.string.navigate_back),
                        tint = Color.White
                    )
                }
                Row {
                    IconButton(onClick = onShareClick) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_share),
                            contentDescription = stringResource(id = R.string.share),
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = onBookmarkClick) {
                        Icon(
                            imageVector = if (bookmarked) ImageVector.vectorResource(id = R.drawable.ic_bookmark_selected)
                            else ImageVector.vectorResource(id = R.drawable.ic_bookmark),
                            contentDescription = if (bookmarked) stringResource(id = R.string.bookmarked)
                            else stringResource(id = R.string.not_bookmarked),
                            tint = Color.White
                        )
                    }
                }
            }
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(top = 32.dp)
            ) {
                CategoryItem(
                    text = sourceName,
                    unchangeableTextColor = true
                )
                Text(
                    text = title,
                    style = typography.headlineMedium,
                    color = Color.White,
                )
                Column {
                    Text(
                        text = author,
                        style = typography.titleLarge,
                        color = Color.White
                    )
                    Text(
                        text = stringResource(R.string.author),
                        style = typography.labelMedium,
                        color = GreyLight
                    )
                }
            }
        }
    }
}

@Composable
fun ArticleBody(
    scrollState: ScrollState,
    headerHeight: Dp,
    topBarsHeight: Dp,
    overlayPadding: Dp,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = topBarsHeight)
            .verticalScroll(scrollState)
    ) {
        Spacer(
            modifier = Modifier.height(headerHeight - (overlayPadding + topBarsHeight))
        )
        Text(
            text = stringResource(id = R.string.terms_and_conditions_text) +
                    stringResource(id = R.string.terms_and_conditions_text),
            style = typography.bodyLarge,
            color = colorScheme.onBackground,
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .background(color = colorScheme.surface)
                .padding(horizontal = 16.dp)
                .padding(top = overlayPadding)
        )
    }
}

@Preview
@Composable
fun ArticleScreenPreview() {
    AggregateTheme {
        ArticleScreenContent(
            article = UiArticle(
                source = UiArticleSource(name = "The Washington Post"),
                author = "Oliver Darcy",
                title = "Fox News' sudden firing of Tucker Carlson may have come down to one simple calculation - CNN",
                url = "",
                urlToImage = "",
                publishedAt = "2023-04-25T08:36",
                bookmarked = false
            ),
            onBackClick = {}
        )
    }
}