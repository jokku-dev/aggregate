package dev.aggregate.designsystem.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.ui.graphics.Color.Companion.White
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val darkColorScheme = darkColorScheme(
    primary = dev.aggregate.designsystem.theme.PurpleLight,
    secondary = dev.aggregate.designsystem.theme.GreyDark,
    onSecondary = dev.aggregate.designsystem.theme.GreyLight,
    surface = dev.aggregate.designsystem.theme.BlackDark,
    onSurface = dev.aggregate.designsystem.theme.GreyPrimary,
    onSurfaceVariant = dev.aggregate.app.designsystem.theme.BlackLight,
    background = dev.aggregate.app.designsystem.theme.BlackDark,
    onBackground = dev.aggregate.app.designsystem.theme.GreyLighter
)

private val lightColorScheme = lightColorScheme(
    primary = dev.aggregate.app.designsystem.theme.PurplePrimary,
    secondary = dev.aggregate.app.designsystem.theme.GreyLighter,
    onSecondary = dev.aggregate.app.designsystem.theme.GreyPrimary,
    surface = White,
    onSurface = dev.aggregate.app.designsystem.theme.GreyLight,
    onSurfaceVariant = dev.aggregate.app.designsystem.theme.BlackPrimary,
    background = White,
    onBackground = dev.aggregate.app.designsystem.theme.GreyDark
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
        typography = dev.aggregate.app.presentation.theme.Typography,
        shapes = dev.aggregate.app.designsystem.theme.Shapes,
        content = content
    )
}