package dev.jokku.aggregate.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import dev.jokku.aggregate.navigation.Navigation
import dev.jokku.designsystem.theme.AggregateTheme
import dev.jokku.ui.BottomBar

private const val HOME = "home"
private const val FAVORITES = "favorites"
private const val SOURCES = "sources"
private const val BOOKMARKS = "bookmarks"
private const val PROFILE = "profile"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AggregateApp(
    startDestination: String,
    navController: NavHostController
) {
    var bottomBarState by rememberSaveable { mutableStateOf(true) }

    dev.jokku.designsystem.theme.AggregateTheme {
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        bottomBarState = when (navBackStackEntry?.destination?.route) {
            HOME, FAVORITES, SOURCES, BOOKMARKS, PROFILE -> true
            else -> false
        }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                dev.jokku.ui.BottomBar(
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