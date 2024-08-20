package dev.jokku.designsystem.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val darkColorScheme = darkColorScheme(
    primary = dev.jokku.designsystem.theme.PurpleLight,
    secondary = dev.jokku.designsystem.theme.GreyDark,
    onSecondary = dev.jokku.designsystem.theme.GreyLight,
    surface = dev.jokku.designsystem.theme.BlackDark,
    onSurface = dev.jokku.designsystem.theme.GreyPrimary,
    onSurfaceVariant = dev.jokku.designsystem.theme.BlackLight,
    background = dev.jokku.designsystem.theme.BlackDark,
    onBackground = dev.jokku.designsystem.theme.GreyLighter
)

private val lightColorScheme = lightColorScheme(
    primary = dev.jokku.designsystem.theme.PurplePrimary,
    secondary = dev.jokku.designsystem.theme.GreyLighter,
    onSecondary = dev.jokku.designsystem.theme.GreyPrimary,
    surface = White,
    onSurface = dev.jokku.designsystem.theme.GreyLight,
    onSurfaceVariant = dev.jokku.designsystem.theme.BlackPrimary,
    background = White,
    onBackground = dev.jokku.designsystem.theme.GreyDark
)

@androidx.compose.runtime.Composable
fun AggregateTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    systemUiController: SystemUiController = rememberSystemUiController(),
    content: @androidx.compose.runtime.Composable () -> Unit
) {
    val useDynamicColor = dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val colorScheme = when {
        useDynamicColor && darkTheme -> dynamicDarkColorScheme(androidx.compose.ui.platform.LocalContext.current)
        useDynamicColor && !darkTheme -> dynamicLightColorScheme(androidx.compose.ui.platform.LocalContext.current)
        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }

    androidx.compose.runtime.SideEffect { systemUiController.setSystemBarsColor(color = colorScheme.surface) }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = dev.jokku.aggregate.presentation.theme.Typography,
        shapes = dev.jokku.designsystem.theme.Shapes,
        content = content
    )
}