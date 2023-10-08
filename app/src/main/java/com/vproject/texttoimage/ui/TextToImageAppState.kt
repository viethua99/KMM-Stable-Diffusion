package com.vproject.texttoimage.ui

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
import com.vproject.texttoimage.core.ui.TrackDisposableJank
import com.vproject.texttoimage.feature.explore.navigation.exploreRoute
import com.vproject.texttoimage.feature.explore.navigation.navigateToExplore
import com.vproject.texttoimage.feature.generate.navigation.generateRoute
import com.vproject.texttoimage.feature.generate.navigation.navigateToGenerate
import com.vproject.texttoimage.feature.settings.navigation.navigateToSettings
import com.vproject.texttoimage.navigation.TopLevelDestination
import com.vproject.texttoimage.navigation.TopLevelDestination.GENERATE
import com.vproject.texttoimage.navigation.TopLevelDestination.EXPLORE

@Composable
fun rememberTextToImageAppState(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController = rememberNavController(),
): TextToImageAppState {
    NavigationTrackingSideEffect(navController)
    return remember(
        navController,
        windowSizeClass) {
        TextToImageAppState(
            navController,
            windowSizeClass)
    }
}

@Stable
class TextToImageAppState(
    val navController: NavHostController,
    val windowSizeClass: WindowSizeClass
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            generateRoute -> GENERATE
            exploreRoute -> EXPLORE
            else -> null
        }

    val shouldShowBottomBar: Boolean
        @Composable get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact &&
                (currentTopLevelDestination == GENERATE || currentTopLevelDestination == EXPLORE)


    /**
     * Map of top level destinations to be used in the TopBar, BottomBar. The key is the
     * route.
     */
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList().filter { it.name == GENERATE.name }


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
            EXPLORE -> navController.navigateToExplore(topLevelNavOptions)
        }
    }

    fun navigateToSettings() {
        navController.navigateToSettings()
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