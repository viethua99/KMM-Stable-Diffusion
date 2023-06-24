package com.vproject.brushai.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.vproject.brushai.feature.generate.navigation.generateRoute
import com.vproject.brushai.feature.generate.navigation.generateScreen

@Composable
fun BrushAiNavHost(modifier: Modifier = Modifier, startDestination: String = generateRoute,
) {
    NavHost(
        navController = rememberNavController(),
        startDestination = startDestination,
        modifier = modifier,
    ) {
        generateScreen()
    }
}