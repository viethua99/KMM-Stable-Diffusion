package com.vproject.brushai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.vproject.brushai.core.designsystem.theme.BrushAiTheme
import com.vproject.brushai.ui.BrushAiApp
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Turn off the decor fitting system windows, which allows us to handle insets,
        // including IME animations
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val systemUiController = rememberSystemUiController()
            val isUseDarkTheme = shouldUseDarkTheme()

            // Update the dark content of the system bars to match the theme
            DisposableEffect(systemUiController, isUseDarkTheme) {
                systemUiController.systemBarsDarkContentEnabled = !isUseDarkTheme
                onDispose {}
            }

            BrushAiTheme(darkTheme = isUseDarkTheme) {
                BrushAiApp(windowSizeClass = calculateWindowSizeClass(this))
            }
        }
    }
}

/**
 * Returns `true` if dark theme should be used, as a function of the [uiState] and the
 * current system context.
 */
@Composable
private fun shouldUseDarkTheme(): Boolean = false