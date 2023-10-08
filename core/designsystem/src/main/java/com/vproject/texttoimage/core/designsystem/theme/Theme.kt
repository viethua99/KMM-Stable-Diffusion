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
    primary = Color(0xFFFFFFFF),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xffec4079),
    onPrimaryContainer = Color(0XFFf06291),
    secondary = Color(0xFFFFFFFF),
    onSecondary = Color(0xFFd81b5f),
    background = Color(0xFF9E9E9E),
    onBackground = Color(0xFFAD1457),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFFAD1457),
)

/**
 * Dark theme color scheme
 */
@VisibleForTesting
val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF0D47A1),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Purple90,
    onPrimaryContainer = Purple10,
    secondary = Color(0xFFFFFFFF),
    onSecondary = Color(0xFFBDBDBD),
    background = DarkPurpleGray99,
    onBackground = DarkPurpleGray10,
    surface = Color(0xFF212121),
    onSurface = Color(0xFFBDBDBD),
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
