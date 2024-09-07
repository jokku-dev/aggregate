package dev.aggregate.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.aggregate.app.ui.AggregateApp
import dev.aggregate.welcome.SplashViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition {
            !splashViewModel.isLoading.value
        }
        actionBar?.hide()

        enableEdgeToEdge()

        setContent {
            val screen by splashViewModel.startDestination
            val navController = rememberNavController()
            AggregateApp(startDestination = screen, navController = navController)
        }
    }
}
