package com.vproject.brushai.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.vproject.brushai.core.ui.TrackDisposableJank
import com.vproject.brushai.feature.generate.navigation.generateRoute
import com.vproject.brushai.feature.generate.navigation.navigateToGenerate
import com.vproject.brushai.navigation.TopLevelDestination
import com.vproject.brushai.navigation.TopLevelDestination.GENERATE
import com.vproject.brushai.navigation.TopLevelDestination.EXPLORE

@Composable
fun rememberBrushAiAppState(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController = rememberNavController(),
): BrushAiAppState {
    NavigationTrackingSideEffect(navController)
    return remember(
        navController,
        windowSizeClass) {
        BrushAiAppState(
            navController,
            windowSizeClass)
    }
}

@Stable
class BrushAiAppState(
    val navController: NavHostController,
    val windowSizeClass: WindowSizeClass
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            generateRoute -> GENERATE
            generateRoute -> EXPLORE
            else -> null
        }

    val shouldShowBottomBar: Boolean
        get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact


    /**
     * Map of top level destinations to be used in the TopBar, BottomBar. The key is the
     * route.
     */
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()


    /**
     * UI logic for navigating to a top level destination in the app. Top level destinations have
     * only one copy of the destination of the back stack, and save and restore state whenever you
     * navigate to and from it.
     *
     * @param topLevelDestination: The destination the app needs to navigate to.
     */
    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when re-selecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }

        when (topLevelDestination) {
            GENERATE -> navController.navigateToGenerate(topLevelNavOptions)
            EXPLORE -> navController.navigateToGenerate(topLevelNavOptions)
        }
    }
}

/**
 * Stores information about navigation events to be used with JankStats
 */
@Composable
private fun NavigationTrackingSideEffect(navController: NavHostController) {
    TrackDisposableJank(navController) { metricsHolder ->
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            metricsHolder.state?.putState("Navigation", destination.route.toString())
        }

        navController.addOnDestinationChangedListener(listener)

        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }
}