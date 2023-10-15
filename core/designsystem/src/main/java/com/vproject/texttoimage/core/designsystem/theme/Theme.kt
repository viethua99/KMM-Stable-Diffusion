package com.vproject.texttoimage.core.designsystem.theme

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Light theme color scheme
 */
@VisibleForTesting
val LightColorScheme = lightColorScheme(
    primary = Blue800,
    onPrimary = White50,
    secondary = White50,
    onSecondary = Grey300,
    surface = White50,
    onSurface = Black900,
)

/**
 * Dark theme color scheme
 */
@VisibleForTesting
val DarkColorScheme = darkColorScheme(
    primary = Blue600,
    onPrimary = White300,
    secondary = White50,
    onSecondary = Grey300,
    surface = Black900,
    onSurface = White300,
)

/**
 * Text To Image theme.
 *
 * @param darkTheme Whether the theme should use a dark color scheme (follows system by default).
 */
@Composable
fun TextToImageTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    // Color scheme
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    // Background theme
    val backgroundTheme = BackgroundTheme(color = colorScheme.surface)

    val tintTheme = TintTheme()

    // Composition locals
    CompositionLocalProvider(
        LocalBackgroundTheme provides backgroundTheme,
        LocalTintTheme provides tintTheme,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = TextToImageTypography,
            content = content,
        )
    }
}
