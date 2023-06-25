package com.vproject.brushai.feature.explore.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.vproject.brushai.feature.explore.ExploreRoute

const val exploreRoute = "explore_route"

fun NavController.navigateToExplore(navOptions: NavOptions? = null) {
    this.navigate(exploreRoute, navOptions)
}

fun NavGraphBuilder.exploreScreen() {
    composable(route = exploreRoute) {
        ExploreRoute()
    }
}