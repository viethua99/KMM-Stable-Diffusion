package com.vproject.brushai.feature.result.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.vproject.brushai.feature.result.ResultRoute

const val resultRoute = "result_route"

fun NavController.navigateToResult(navOptions: NavOptions? = null) {
    this.navigate(resultRoute, navOptions)
}

fun NavGraphBuilder.resultScreen() {
    composable(route = resultRoute) {
        ResultRoute()
    }
}