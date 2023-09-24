package com.vproject.texttoimage.core.designsystem

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import com.vproject.texttoimage.core.designsystem.theme.BackgroundTheme
import com.vproject.texttoimage.core.designsystem.theme.TextToImageTheme
import com.vproject.texttoimage.core.designsystem.theme.DarkColorScheme
import com.vproject.texttoimage.core.designsystem.theme.GradientColors
import com.vproject.texttoimage.core.designsystem.theme.LightColorScheme
import com.vproject.texttoimage.core.designsystem.theme.LocalBackgroundTheme
import com.vproject.texttoimage.core.designsystem.theme.LocalGradientColors
import com.vproject.texttoimage.core.designsystem.theme.LocalTintTheme
import com.vproject.texttoimage.core.designsystem.theme.TintTheme
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests [TextToImageTheme] using between light and dark theme:
 * It verifies that the various composition locals — [MaterialTheme], [LocalGradientColors] and
 * [LocalBackgroundTheme] — have the expected values for a given theme mode, as specified by the
 * design system.
 */
class ThemeTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun givenDarkThemeDisabled_whenThemeApplied_thenCurrentThemeIsLight() {
        composeTestRule.setContent {
            TextToImageTheme(darkTheme = false) {
                val colorScheme = LightColorScheme
                assertColorSchemesEqual(colorScheme, MaterialTheme.colorScheme)

                val gradientColors = GradientColors(
                    top = colorScheme.inverseOnSurface,
                    bottom = colorScheme.primaryContainer,
                    container = colorScheme.surface,
                )
                assertEquals(gradientColors, LocalGradientColors.current)

                val backgroundTheme = BackgroundTheme(
                    color = colorScheme.surface,
                    tonalElevation = 2.dp,
                )
                assertEquals(backgroundTheme, LocalBackgroundTheme.current)

                val tintTheme = TintTheme()
                assertEquals(tintTheme, LocalTintTheme.current)
            }
        }
    }

    @Test
    fun givenDarkThemeEnabled_whenThemeApplied_thenCurrentThemeIsDark() {
        composeTestRule.setContent {
            TextToImageTheme(darkTheme = true) {
                val colorScheme = DarkColorScheme
                assertColorSchemesEqual(colorScheme, MaterialTheme.colorScheme)

                val gradientColors = GradientColors(
                    top = colorScheme.inverseOnSurface,
                    bottom = colorScheme.primaryContainer,
                    container = colorScheme.surface,
                )
                assertEquals(gradientColors, LocalGradientColors.current)

                val backgroundTheme = BackgroundTheme(
                    color = colorScheme.surface,
                    tonalElevation = 2.dp,
                )
                assertEquals(backgroundTheme, LocalBackgroundTheme.current)

                val tintTheme = TintTheme()
                assertEquals(tintTheme, LocalTintTheme.current)
            }
        }
    }

    private fun assertColorSchemesEqual(
        expectedColorScheme: ColorScheme,
        actualColorScheme: ColorScheme,
    ) {
        assertEquals(expectedColorScheme.primary, actualColorScheme.primary)
        assertEquals(expectedColorScheme.onPrimary, actualColorScheme.onPrimary)
        assertEquals(expectedColorScheme.primaryContainer, actualColorScheme.primaryContainer)
        assertEquals(expectedColorScheme.onPrimaryContainer, actualColorScheme.onPrimaryContainer)
        assertEquals(expectedColorScheme.secondary, actualColorScheme.secondary)
        assertEquals(expectedColorScheme.onSecondary, actualColorScheme.onSecondary)
        assertEquals(expectedColorScheme.secondaryContainer, actualColorScheme.secondaryContainer)
        assertEquals(
            expectedColorScheme.onSecondaryContainer,
            actualColorScheme.onSecondaryContainer,
        )
        assertEquals(expectedColorScheme.tertiary, actualColorScheme.tertiary)
        assertEquals(expectedColorScheme.onTertiary, actualColorScheme.onTertiary)
        assertEquals(expectedColorScheme.tertiaryContainer, actualColorScheme.tertiaryContainer)
        assertEquals(expectedColorScheme.onTertiaryContainer, actualColorScheme.onTertiaryContainer)
        assertEquals(expectedColorScheme.error, actualColorScheme.error)
        assertEquals(expectedColorScheme.onError, actualColorScheme.onError)
        assertEquals(expectedColorScheme.errorContainer, actualColorScheme.errorContainer)
        assertEquals(expectedColorScheme.onErrorContainer, actualColorScheme.onErrorContainer)
        assertEquals(expectedColorScheme.background, actualColorScheme.background)
        assertEquals(expectedColorScheme.onBackground, actualColorScheme.onBackground)
        assertEquals(expectedColorScheme.surface, actualColorScheme.surface)
        assertEquals(expectedColorScheme.onSurface, actualColorScheme.onSurface)
        assertEquals(expectedColorScheme.surfaceVariant, actualColorScheme.surfaceVariant)
        assertEquals(expectedColorScheme.onSurfaceVariant, actualColorScheme.onSurfaceVariant)
        assertEquals(expectedColorScheme.inverseSurface, actualColorScheme.inverseSurface)
        assertEquals(expectedColorScheme.inverseOnSurface, actualColorScheme.inverseOnSurface)
        assertEquals(expectedColorScheme.outline, actualColorScheme.outline)
    }
}