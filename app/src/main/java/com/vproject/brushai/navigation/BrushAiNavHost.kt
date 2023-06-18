package com.vproject.brushai.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.vproject.brushai.feature.prompt.navigation.promptRoute
import com.vproject.brushai.feature.prompt.navigation.promptScreen

@Composable
fun BrushAiNavHost(modifier: Modifier = Modifier, startDestination: String = promptRoute,
) {
    NavHost(
        navController = rememberNavController(),
        startDestination = startDestination,
        modifier = modifier,
    ) {
        promptScreen()
    }
}