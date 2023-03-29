package com.jokku.aggregate.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColorScheme(
    primary = PurpleLight,
    secondary = GreyDark,
    onSecondary = GreyLight,
    surface = BlackDark,
    onSurface = GreyPrimary,
    onSurfaceVariant = BlackLight,
    onBackground = GreyLighter
)

private val LightColorScheme = lightColorScheme(
    primary = PurplePrimary,
    secondary = GreyLighter,
    onSecondary = GreyPrimary,
    surface = White,
    onSurface = GreyLight,
    onSurfaceVariant = BlackPrimary,
    onBackground = GreyDark
)

@Composable
fun AggregateTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    systemUiController: SystemUiController = rememberSystemUiController(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    SideEffect { systemUiController.setSystemBarsColor(color = colorScheme.surface) }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}