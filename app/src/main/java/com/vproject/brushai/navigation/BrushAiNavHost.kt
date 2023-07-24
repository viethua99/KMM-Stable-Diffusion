package com.vproject.brushai.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.vproject.brushai.feature.explore.navigation.exploreScreen
import com.vproject.brushai.feature.generate.navigation.generateRoute
import com.vproject.brushai.feature.generate.navigation.generateScreen
import com.vproject.brushai.feature.settings.navigation.settingsScreen
import com.vproject.brushai.ui.BrushAiAppState

@Composable
fun BrushAiNavHost(
    appState: BrushAiAppState,
    modifier: Modifier = Modifier,
    startDestination: String = generateRoute,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        generateScreen()
        exploreScreen()
        settingsScreen()
    }
}