package com.vproject.brushai.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.vproject.feature.home.navigation.homeRoute
import com.vproject.feature.home.navigation.homeScreen

@Composable
fun BrushAiNavHost(modifier: Modifier = Modifier, startDestination: String = homeRoute,
) {
    NavHost(
        navController = rememberNavController(),
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeScreen()
    }
}