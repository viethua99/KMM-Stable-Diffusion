package com.vproject.brushai.feature.prompt.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.vproject.brushai.feature.prompt.PromptRoute


const val promptRoute = "prompt_route"

fun NavController.navigateToPrompt(navOptions: NavOptions? = null) {
    this.navigate(promptRoute, navOptions)
}

fun NavGraphBuilder.promptScreen() {
    composable(route = promptRoute) {
        PromptRoute()
    }
}