package com.vproject.stablediffusion.presentation.component.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

/**
 * Light theme color scheme
 */
private val LightColorScheme = lightColorScheme(
    primary = Blue400,
    secondary = Green400,
    tertiary = Blue400,
    background = Color.White,
    surface = LightGray400,
    surfaceVariant = Gray400,
    onPrimary = Color.White,
    onSecondary = LightGray400,
    onBackground = Black,
    onSurface = Black,
    onSurfaceVariant = DarkGray400,
    outline = LightGray400
)

/**
 * Dark theme color scheme
 */
private val DarkColorScheme = darkColorScheme(
    primary = Blue400,
    secondary = Green400,
    tertiary = Blue400,
    background = Black,
    surface = DarkGray400,
    surfaceVariant = Gray400,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    onSurfaceVariant = LightGray400,
    outline = LightGray400
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

    // Composition locals
    MaterialTheme(
        colorScheme = colorScheme,
        typography = StableDiffusionAppTypography,
        content = content,
    )
}