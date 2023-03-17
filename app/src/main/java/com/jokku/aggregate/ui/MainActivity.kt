package com.jokku.aggregate.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.jokku.aggregate.ui.nav.Navigation
import com.jokku.aggregate.ui.theme.AggregateTheme
import com.jokku.aggregate.ui.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            !viewModel.isLoading.value
        }
        actionBar?.hide()
        setContent {
            AggregateTheme {
                val screen by viewModel.startDestination
                val navController = rememberNavController()
                Navigation(navController = navController, startDestination = screen)
            }
        }

    }
}