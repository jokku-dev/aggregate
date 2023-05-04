package com.jokku.aggregate.ui.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jokku.aggregate.R
import com.jokku.aggregate.ui.nav.Screen
import com.jokku.aggregate.ui.theme.AggregateTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleTopBar(
    sourceName: String,
    url: String,
    bookmarked: Boolean,
    headerHeightPx: Float,
    topBarHeightPx: Float,
    scrollState: ScrollState,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    onBookmarkClick: () -> Unit,
    modifier: Modifier = Modifier,
    colorScheme: ColorScheme = MaterialTheme.colorScheme,
    systemUiController: SystemUiController = rememberSystemUiController()
) {
    val topBarBottom by remember { mutableStateOf(headerHeightPx - topBarHeightPx) }
    // derived state triggers recomposition only if result changes and not if changes any inner state
    // so we use it to reduce unnecessary recompositions when some inner state changes
    val showTopBar by remember { derivedStateOf { scrollState.value >= topBarBottom } }

    LaunchedEffect(key1 = showTopBar) {
        if (showTopBar) systemUiController.setStatusBarColor(color = colorScheme.surface)
        else systemUiController.setStatusBarColor(color = Color.Transparent)
    }

    AnimatedVisibility(
        modifier = modifier,
        visible = showTopBar,
        enter = fadeIn(animationSpec = tween(300)),
        exit = fadeOut(animationSpec = tween(300))
    ) {
        CenterAlignedTopAppBar(
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_back),
                        contentDescription = stringResource(id = R.string.navigate_back)
                    )
                }
            },
            title = {
                Text(
                    text = sourceName,
                    style = typography.headlineMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Clip,
                    softWrap = false
                )
            },
            actions = {
                IconButton(onClick = onShareClick) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_share),
                        contentDescription = stringResource(id = R.string.share)
                    )
                }
                IconButton(onClick = onBookmarkClick) {
                    Icon(
                        imageVector = if (bookmarked) ImageVector.vectorResource(id = R.drawable.ic_bookmark_selected)
                        else ImageVector.vectorResource(id = R.drawable.ic_bookmark),
                        contentDescription = if (bookmarked) stringResource(id = R.string.bookmarked)
                        else stringResource(id = R.string.not_bookmarked)
                    )
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = colorScheme.surface,
                navigationIconContentColor = colorScheme.onSecondary,
                titleContentColor = colorScheme.onSecondary,
                actionIconContentColor = colorScheme.onSecondary
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar(
    navController: NavHostController,
    visible: Boolean
) {
    val items = listOf(Screen.Home, Screen.Sources, Screen.Bookmarks, Screen.Profile)

    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
    ) {
        NavigationBar(
            modifier = Modifier
                .graphicsLayer {
                    shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                    clip = true
                    shadowElevation = 10f
                },
            containerColor = colorScheme.surface,
            tonalElevation = 0.dp
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            items.forEach { item ->
                NavigationBarItem(
                    icon = {
                        Column(horizontalAlignment = CenterHorizontally) {
                            if (item.badgeCount > 0) {
                                BadgedBox(
                                    badge = {
                                        Badge {
                                            Text(
                                                text = item.badgeCount.toString(),
                                                style = typography.displaySmall,
                                                color = colorScheme.surface
                                            )
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = ImageVector.vectorResource(id = item.icon),
                                        contentDescription = item.route
                                    )
                                }
                            } else {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = item.icon),
                                    contentDescription = item.route
                                )
                            }
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = colorScheme.primary,
                        unselectedIconColor = colorScheme.onSurface,
                        indicatorColor = colorScheme.surface
                    ),
                    // Defines whether any of hierarchy destinations and item roots are the same
                    // (in cases of nested navigation)
                    selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                    onClick = {
                        navController.navigate(item.route) {
                            // pops all previous destinations up to start destination
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun ArticleTopBarPreview() {
    AggregateTheme {
        ArticleTopBar(
            sourceName = "Source Name",
            url = "",
            bookmarked = true,
            scrollState = rememberScrollState(),
            headerHeightPx = 0f,
            topBarHeightPx = 0f,
            onBackClick = {},
            onShareClick = {},
            onBookmarkClick = {}
        )
    }
}

@Preview
@Composable
fun BottomBarPreview() {
    AggregateTheme {
        BottomBar(
            navController = rememberNavController(),
            visible = true
        )
    }
}