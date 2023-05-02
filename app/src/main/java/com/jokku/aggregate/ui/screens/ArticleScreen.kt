package com.jokku.aggregate.ui.screens

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jokku.aggregate.R
import com.jokku.aggregate.ui.nav.Screen
import com.jokku.aggregate.ui.theme.AggregateTheme
import com.jokku.aggregate.ui.theme.GreyLight
import com.jokku.aggregate.ui.viewmodel.Article
import com.jokku.aggregate.ui.viewmodel.MainNewsViewModel
import com.jokku.aggregate.ui.viewmodel.NewsViewModel
import com.jokku.aggregate.ui.views.ArticleTopBar

@Composable
fun ArticleScreen(
    navController: NavHostController,
    viewModel: NewsViewModel = hiltViewModel<MainNewsViewModel>(),
    systemUiController: SystemUiController = rememberSystemUiController(),
) {
    val state = viewModel.articleState.collectAsStateWithLifecycle().value

    ArticleScreenContent(
        article = state.article,
        navController = navController
    )
    SideEffect { systemUiController.setStatusBarColor(color = Color.Transparent) }
}

@Composable
fun ArticleScreenContent(
    article: Article,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    headerHeight: Dp = 400.dp,
    topAndSystemBarHeight: Dp = 88.dp
) {
    val scrollState = rememberScrollState()
    // Density scope provides conversion to pixels
    val headerHeightPx = with(LocalDensity.current) { headerHeight.toPx() }
    val topBarHeightPx = with(LocalDensity.current) { topAndSystemBarHeight.toPx() }

    Box(modifier = modifier) {
        ArticleHeader(
            image = painterResource(id = article.image),
            title = article.title,
            sourceName = article.sourceName,
            author = article.author,
            bookmarked = article.bookmarked,
            state = scrollState,
            navController = navController,
            headerHeightPx = headerHeightPx,
            modifier = Modifier
                .fillMaxWidth()
                .height(headerHeight)
        )
        ArticleBody(
            state = scrollState,
            headerHeight = headerHeight,
            modifier = Modifier.fillMaxSize()
        )
        ArticleTopBar(
            bookmarked = article.bookmarked,
            sourceName = article.sourceName,
            url = article.url,
            navController = navController,
            state = scrollState,
            headerHeightPx = headerHeightPx,
            topBarHeightPx = topBarHeightPx
        )
    }
}

@Composable
fun ArticleHeader(
    image: Painter,
    title: String,
    sourceName: String,
    author: String,
    bookmarked: Boolean,
    navController: NavHostController,
    state: ScrollState,
    headerHeightPx: Float,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            // lambda version of graphicsLayer to avoid unnecessary recompositions
            // which induce bad performance
            .graphicsLayer {
                // parallax effect
                translationY = -state.value.toFloat() / 2f
                // fade animation using affine function
                alpha = (-1f / headerHeightPx) * state.value + 1
            },
    ) {
        Image(
            painter = image,
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
            modifier = Modifier.padding(top = 48.dp, bottom = 32.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                IconButton(
                    onClick = {
                        navController.navigate(
                            route = Screen.Bookmarks.route,
                            navOptions = NavOptions.Builder()
                                .setPopUpTo(route = Screen.Bookmarks.route, inclusive = true)
                                .build()
                        )
                    }
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_back),
                        contentDescription = stringResource(id = R.string.navigate_back),
                        tint = Color.White
                    )
                }
                Column {
                    IconButton(
                        onClick = {

                        },
                    ) {
                        Icon(
                            imageVector = if (bookmarked) ImageVector.vectorResource(id = R.drawable.ic_bookmark_selected)
                            else ImageVector.vectorResource(id = R.drawable.ic_bookmark),
                            contentDescription = if (bookmarked) stringResource(id = R.string.bookmarked)
                            else stringResource(id = R.string.not_bookmarked),
                            tint = Color.White
                        )
                    }
                    IconButton(
                        onClick = {

                        },
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_share),
                            contentDescription = stringResource(id = R.string.share),
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
    state: ScrollState,
    headerHeight: Dp,
    modifier: Modifier = Modifier,
    overlayPadding: Dp = 32.dp
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.verticalScroll(state)
    ) {
        Spacer(
            modifier = Modifier.height(headerHeight - overlayPadding)
        )
        repeat(2) {
            Text(
                text = stringResource(id = R.string.terms_and_conditions_text),
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
}

@Preview()
@Composable
fun ArticleScreenPreview() {
    AggregateTheme {
        ArticleScreenContent(
            article = Article(
                sourceName = "The Washington Post",
                author = "Oliver Darcy",
                title = "Fox News' sudden firing of Tucker Carlson may have come down to one simple calculation - CNN",
                url = "",
                image = R.drawable.img_news_mock_1,
                publishedAt = "2023-04-25T08:36",
                bookmarked = false
            ),
            navController = rememberNavController()
        )
    }
}