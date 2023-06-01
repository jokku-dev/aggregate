package com.jokku.aggregate.presentation.theme

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

private val darkColorScheme = darkColorScheme(
    primary = PurpleLight,
    secondary = GreyDark,
    onSecondary = GreyLight,
    surface = BlackDark,
    onSurface = GreyPrimary,
    onSurfaceVariant = BlackLight,
    background = BlackDark,
    onBackground = GreyLighter
)

private val lightColorScheme = lightColorScheme(
    primary = PurplePrimary,
    secondary = GreyLighter,
    onSecondary = GreyPrimary,
    surface = White,
    onSurface = GreyLight,
    onSurfaceVariant = BlackPrimary,
    background = White,
    onBackground = GreyDark
)

@Composable
fun AggregateTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    systemUiController: SystemUiController = rememberSystemUiController(),
    content: @Composable () -> Unit
) {
    val useDynamicColor = dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val colorScheme = when {
        useDynamicColor && darkTheme -> dynamicDarkColorScheme(LocalContext.current)
        useDynamicColor && !darkTheme -> dynamicLightColorScheme(LocalContext.current)
        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }

    SideEffect { systemUiController.setSystemBarsColor(color = colorScheme.surface) }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}