package dev.aggregate.article

import androidx.activity.OnBackPressedCallback
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density.toPx
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.aggregate.app.article.R
import dev.aggregate.designsystem.theme.AggregateTheme
import dev.aggregate.designsystem.theme.GreyLight
import dev.aggregate.presentation.model.UiArticle
import dev.aggregate.presentation.model.UiArticleSource
import dev.aggregate.presentation.navigation.Screen
import dev.aggregate.ui.ArticleTopBar
import kotlin.apply
import kotlin.text.toFloat

private const val OTHER = "other"

@androidx.compose.runtime.Composable
fun ArticleScreen(
    navController: NavHostController,
    viewModel: dev.aggregate.app.NewsViewModel = hiltViewModel<dev.aggregate.app.BaseNewsViewModel>(),
    systemUiController: SystemUiController = rememberSystemUiController(),
    colorScheme: ColorScheme = MaterialTheme.colorScheme
) {
    val state by viewModel.articleState.collectAsStateWithLifecycle()
    var currentScreen by androidx.compose.runtime.remember {
        androidx.compose.runtime.mutableStateOf(
            dev.aggregate.app.presentation.navigation.Screen.ArticleScreen.route
        )
    }

    val onBackPressedDispatcher =
        androidx.activity.compose.LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val lifecycleOwner = androidx.compose.ui.platform.LocalLifecycleOwner.current

    ArticleScreenContent(
        article = state.article,
        onBackClick = {
            currentScreen = OTHER
            navController.popBackStack()
        }
    )

    androidx.compose.runtime.LaunchedEffect(key1 = currentScreen) {
        if (currentScreen == dev.aggregate.app.presentation.navigation.Screen.ArticleScreen.route) {
            systemUiController.setStatusBarColor(color = androidx.compose.ui.graphics.Color.Transparent)
        } else {
            systemUiController.setStatusBarColor(color = colorScheme.surface)
        }
    }
    // On back pressed handling to bring back the color of the status bar
    androidx.compose.runtime.DisposableEffect(key1 = Unit) {
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

@androidx.compose.runtime.Composable
fun ArticleScreenContent(
    article: dev.aggregate.app.presentation.model.UiArticle,
    onBackClick: () -> Unit,
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier,
    headerHeight: androidx.compose.ui.unit.Dp = 400.dp,
    heightToShowTopBar: androidx.compose.ui.unit.Dp = 108.dp,
    topBarsHeight: androidx.compose.ui.unit.Dp = 84.dp,
    overlayPadding: androidx.compose.ui.unit.Dp = 32.dp
) {
    val scrollState = rememberScrollState()
    // Density scope provides conversion to pixels
    val headerHeightPx =
        with(androidx.compose.ui.platform.LocalDensity.current) { headerHeight.toPx() }
    val topBarHeightPx =
        with(androidx.compose.ui.platform.LocalDensity.current) { heightToShowTopBar.toPx() }

    Box(
        modifier = modifier,
        contentAlignment = androidx.compose.ui.Alignment.TopCenter
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
            modifier = androidx.compose.ui.Modifier.height(headerHeight)
        )
        ArticleBody(
            scrollState = scrollState,
            headerHeight = headerHeight,
            topBarsHeight = topBarsHeight,
            overlayPadding = overlayPadding
        )
        dev.aggregate.app.ui.ArticleTopBar(
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
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier
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
        // AsyncImage(model = urlToImage, contentDescription =)
        Image(
            painter = androidx.compose.ui.res.painterResource(id = dev.aggregate.app.R.drawable.img_news_mock_1),
            contentDescription = null,
            contentScale = androidx.compose.ui.layout.ContentScale.Crop,
            colorFilter = androidx.compose.ui.graphics.ColorFilter.colorMatrix(
                androidx.compose.ui.graphics.ColorMatrix().apply {
                    setToScale(
                        0.5f,
                        0.5f,
                        0.5f,
                        1f
                    )
                }
            ),
            modifier = androidx.compose.ui.Modifier.fillMaxSize(),
        )
        Column(
            horizontalAlignment = androidx.compose.ui.Alignment.Start,
            modifier = androidx.compose.ui.Modifier.padding(top = 36.dp, bottom = 32.dp)
        ) {
            Row(
                modifier = androidx.compose.ui.Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = androidx.compose.ui.Alignment.Top
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = androidx.compose.ui.graphics.vector.ImageVector.vectorResource(
                            id = dev.aggregate.app.R.drawable.ic_arrow_back
                        ),
                        contentDescription = androidx.compose.ui.res.stringResource(id = R.string.navigate_back),
                        tint = androidx.compose.ui.graphics.Color.White
                    )
                }
                Row {
                    IconButton(onClick = onShareClick) {
                        Icon(
                            imageVector = androidx.compose.ui.graphics.vector.ImageVector.vectorResource(
                                id = dev.aggregate.app.R.drawable.ic_share
                            ),
                            contentDescription = androidx.compose.ui.res.stringResource(id = R.string.share),
                            tint = androidx.compose.ui.graphics.Color.White
                        )
                    }
                    IconButton(onClick = onBookmarkClick) {
                        Icon(
                            imageVector = if (bookmarked) {
                                androidx.compose.ui.graphics.vector.ImageVector.vectorResource(
                                    id = dev.aggregate.app.R.drawable.ic_bookmark_selected
                                )
                            } else {
                                ImageVector.vectorResource(id = R.drawable.ic_bookmark)
                            },
                            contentDescription = if (bookmarked) {
                                androidx.compose.ui.res.stringResource(
                                    id = dev.aggregate.app.R.string.bookmarked
                                )
                            } else {
                                androidx.compose.ui.res.stringResource(id = R.string.not_bookmarked)
                            },
                            tint = androidx.compose.ui.graphics.Color.White
                        )
                    }
                }
            }
            Column(
                horizontalAlignment = androidx.compose.ui.Alignment.Start,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = androidx.compose.ui.Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(top = 32.dp)
            ) {
                dev.aggregate.app.ui.CategoryItem(
                    text = sourceName,
                    unchangeableTextColor = true
                )
                Text(
                    text = title,
                    style = typography.headlineMedium,
                    color = androidx.compose.ui.graphics.Color.White,
                )
                Column {
                    Text(
                        text = author,
                        style = typography.titleLarge,
                        color = androidx.compose.ui.graphics.Color.White
                    )
                    Text(
                        text = androidx.compose.ui.res.stringResource(dev.aggregate.app.R.string.author),
                        style = typography.labelMedium,
                        color = dev.aggregate.app.designsystem.theme.GreyLight
                    )
                }
            }
        }
    }
}

@androidx.compose.runtime.Composable
fun ArticleBody(
    scrollState: ScrollState,
    headerHeight: androidx.compose.ui.unit.Dp,
    topBarsHeight: androidx.compose.ui.unit.Dp,
    overlayPadding: androidx.compose.ui.unit.Dp,
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = topBarsHeight)
            .verticalScroll(scrollState)
    ) {
        Spacer(
            modifier = androidx.compose.ui.Modifier.height(headerHeight - (overlayPadding + topBarsHeight))
        )
        Text(
            text = stringResource(id = dev.aggregate.app.R.string.terms_and_conditions_text) +
                    stringResource(id = dev.aggregate.app.R.string.terms_and_conditions_text),
            style = typography.bodyLarge,
            color = colorScheme.onBackground,
            modifier = androidx.compose.ui.Modifier
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .background(color = colorScheme.surface)
                .padding(horizontal = 16.dp)
                .padding(top = overlayPadding)
        )
    }
}

@Preview
@androidx.compose.runtime.Composable
fun ArticleScreenPreview() {
    dev.aggregate.app.designsystem.theme.AggregateTheme {
        ArticleScreenContent(
            article = dev.aggregate.app.presentation.model.UiArticle(
                source = dev.aggregate.app.presentation.model.UiArticleSource(name = "The Washington Post"),
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
