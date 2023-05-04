package com.jokku.aggregate.ui

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
import com.jokku.aggregate.ui.nav.Navigation
import com.jokku.aggregate.ui.theme.AggregateTheme
import com.jokku.aggregate.ui.views.BottomBar

private const val HOME = "home"
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

    AggregateTheme {
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        bottomBarState = when (navBackStackEntry?.destination?.route) {
            HOME, SOURCES, BOOKMARKS, PROFILE -> true
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