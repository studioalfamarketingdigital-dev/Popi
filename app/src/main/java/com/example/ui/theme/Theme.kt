package com.example.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val PipoLightColorScheme = lightColorScheme(
    primary = PipoPrimaryGreen,
    onPrimary = PipoBlack,
    primaryContainer = PipoNeonGreen,
    onPrimaryContainer = PipoBlack,
    secondary = PipoDarkGreen,
    onSecondary = PipoWhite,
    secondaryContainer = PipoGrayLight,
    onSecondaryContainer = PipoBlack,
    tertiary = PipoFireOrange,
    onTertiary = PipoWhite,
    background = PipoWhite,
    onBackground = PipoBlack,
    surface = PipoWhite,
    onSurface = PipoBlack,
    surfaceVariant = PipoGrayLight,
    onSurfaceVariant = PipoBlack,
    outline = PipoCardBorder
)

@Composable
fun PipoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                window.statusBarColor = PipoWhite.toArgb()
                window.navigationBarColor = PipoWhite.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
                WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = true
            }
        }
    }

    MaterialTheme(
        colorScheme = PipoLightColorScheme,
        typography = Typography,
        content = content
    )
}
