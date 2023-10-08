package com.vproject.texttoimage.feature.generate.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.vproject.texttoimage.feature.generate.GenerateRoute


const val generateRoute = "generate_route"

fun NavController.navigateToGenerate(navOptions: NavOptions? = null) {
    this.navigate(generateRoute, navOptions)
}

fun NavGraphBuilder.generateScreen(onGenerateButtonClicked: (prompt: String, selectedStyleId: String) -> Unit) {
    composable(route = generateRoute) {
        GenerateRoute(onGenerateButtonClicked = onGenerateButtonClicked)
    }
}