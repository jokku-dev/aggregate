package com.jokku.aggregate.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColorScheme(
    primary = PurpleLight,
    onPrimary = Color.White,

    secondary = GreyPrimary,
    onSecondary = GreyLight,

    surface = BlackDark,
    onSurface = GreyLight,

    outline = PurpleLight,
    outlineVariant = GreyPrimary
)

private val LightColorScheme = lightColorScheme(
    primary = PurplePrimary,
    onPrimary = Color.White,

    secondary = GreyLighter,
    onSecondary = GreyPrimary,
    onSecondaryContainer = GreyDark,

    surface = Color.White,
    onSurface = GreyDark,
    onSurfaceVariant = BlackPrimary,

    outline = PurplePrimary,
    outlineVariant = GreyLighter
)

@Composable
fun AggregateTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    systemUiController: SystemUiController = rememberSystemUiController(),
    // Dynamic color is available on Android 12+
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
    val view = LocalView.current
    val window = (view.context as Activity).window
    if (!view.isInEditMode) {
        SideEffect {
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }
    SideEffect {
        systemUiController.setSystemBarsColor(color = colorScheme.surface)
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}