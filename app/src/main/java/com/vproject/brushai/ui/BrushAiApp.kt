package com.vproject.brushai.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.vproject.brushai.core.designsystem.component.BrushAiBackground
import com.vproject.brushai.core.designsystem.component.BrushAiGradientBackground
import com.vproject.brushai.core.designsystem.component.BrushAiNavigationBar
import com.vproject.brushai.core.designsystem.component.BrushAiNavigationBarItem
import com.vproject.brushai.core.designsystem.component.BrushAiTopAppBar
import com.vproject.brushai.core.designsystem.icon.BrushAiIcons
import com.vproject.brushai.core.designsystem.theme.GradientColors
import com.vproject.brushai.core.designsystem.theme.LocalGradientColors
import com.vproject.brushai.navigation.BrushAiNavHost
import com.vproject.brushai.navigation.TopLevelDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrushAiApp(
    windowSizeClass: WindowSizeClass,
    appState: BrushAiAppState = rememberBrushAiAppState(
        windowSizeClass = windowSizeClass,
    )
) {
    val shouldShowGradientBackground = appState.currentTopLevelDestination == TopLevelDestination.GENERATE
    BrushAiBackground {
        BrushAiGradientBackground(
            gradientColors = if (shouldShowGradientBackground) {
                LocalGradientColors.current
            } else {
                GradientColors()
            },
        ) {
            Scaffold(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onBackground,
                contentWindowInsets = WindowInsets(0, 0, 0, 0),
                bottomBar = {
                    if (appState.shouldShowBottomBar) {
                        BrushAiBottomBar(
                            destinations = appState.topLevelDestinations,
                            onNavigateToDestination = appState::navigateToTopLevelDestination,
                            currentDestination = appState.currentDestination,
                        )
                    }
                }
            ) { paddingValues ->
                Column(Modifier.fillMaxSize()) {
                    // Show the top app bar on top level destinations.
                    val topLevelDestination = appState.currentTopLevelDestination
                    topLevelDestination?.let { nonNullTopLevelDestination ->
                        BrushAiTopAppBar(
                            titleRes = nonNullTopLevelDestination.titleTextId,
                            actionIcon = BrushAiIcons.RoundedSettings,
                            actionIconContentDescription = null,
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = Color.Transparent,
                            ),
                        )
                    }
                    BrushAiNavHost(appState = appState)
                }
            }
        }
    }
}

@Composable
private fun BrushAiBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    BrushAiNavigationBar(
        modifier = modifier,
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            BrushAiNavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination)},
                icon = {
                    Icon(
                        imageVector = destination.unselectedIcon,
                        contentDescription = null,
                    )
                },
                selectedIcon = {
                    Icon(
                        imageVector = destination.selectedIcon,
                        contentDescription = null,
                    )
                },
                label = { Text(stringResource(destination.iconTextId)) })
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false