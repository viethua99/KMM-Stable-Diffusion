package com.vproject.stablediffusion.presentation.component.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

/**
 * Light theme color scheme
 */
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
val DarkColorScheme = darkColorScheme(
    primary = Blue600,
    onPrimary = White300,
    secondary = White50,
    onSecondary = Grey300,
    surface = Black900,
    onSurface = White300,
)

/**
 * Stable Diffusion app theme.
 *
 * @param darkTheme Whether the theme should use a dark color scheme (follows system by default).
 */
@Composable
fun StableDiffusionAppTheme(
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
            typography = StableDiffusionAppTypography,
            content = content,
        )
    }
}