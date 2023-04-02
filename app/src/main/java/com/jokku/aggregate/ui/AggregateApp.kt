package com.jokku.aggregate.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.jokku.aggregate.ui.nav.Navigation
import com.jokku.aggregate.ui.theme.AggregateTheme
import com.jokku.aggregate.ui.views.BottomBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AggregateApp(
    startDestination: String,
    navController: NavHostController
) {
    val bottomBarState = rememberSaveable { mutableStateOf(true) }

    AggregateTheme {
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        when (navBackStackEntry?.destination?.route) {
            "home" -> bottomBarState.value = true
            "categories" -> bottomBarState.value = true
            "bookmarks" -> bottomBarState.value = true
            "profile" -> bottomBarState.value = true
            else -> bottomBarState.value = false
        }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomBar(
                    navController = navController,
                    bottomBarState = bottomBarState
                )
            }
        ) { padding ->
            Navigation(
                modifier = Modifier.padding(padding),
                navController = navController,
                startDestination = startDestination
            )
        }
    }
}