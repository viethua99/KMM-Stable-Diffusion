package com.vproject.texttoimage.feature.loading.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.vproject.texttoimage.feature.loading.LoadingRoute

const val loadingRoute = "loading_route"

fun NavController.navigateToLoading(navOptions: NavOptions? = null) {
    this.navigate(loadingRoute, navOptions)
}

fun NavGraphBuilder.loadingScreen() {
    composable(route = loadingRoute) {
        LoadingRoute()
    }
}