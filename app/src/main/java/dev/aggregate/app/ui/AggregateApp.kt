package dev.aggregate.app.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import dev.aggregate.app.navigation.Navigation
import dev.aggregate.designsystem.component.BottomBar
import dev.aggregate.designsystem.theme.AggregateTheme

private const val HOME = "home"
private const val FAVORITES = "favorites"
private const val SOURCES = "sources"
private const val BOOKMARKS = "bookmarks"
private const val PROFILE = "profile"

@Composable
fun AggregateApp(
    startDestination: String,
    navController: NavHostController
) {
    var bottomBarState by rememberSaveable { mutableStateOf(true) }

    AggregateTheme {
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        bottomBarState = when (navBackStackEntry?.destination?.route) {
            HOME, FAVORITES, SOURCES, BOOKMARKS, PROFILE -> true
            else -> false
        }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomBar(
                    navController = navController,
                    visible = bottomBarState
                )
            }
        ) { padding -> // padding from elements (bars) of this Scaffold
            Box(modifier = Modifier.padding(bottom = padding.calculateBottomPadding())) {
                Navigation(
                    navController = navController,
                    startDestination = startDestination
                )
            }
        }
    }
}
