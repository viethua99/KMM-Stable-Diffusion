package com.vproject.texttoimage.ui

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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.vproject.texttoimage.core.designsystem.component.TextToImageBackground
import com.vproject.texttoimage.core.designsystem.component.TextToImageGradientBackground
import com.vproject.texttoimage.core.designsystem.component.TextToImageNavigationBar
import com.vproject.texttoimage.core.designsystem.component.TextToImageNavigationBarItem
import com.vproject.texttoimage.core.designsystem.component.TextToImageTopAppBar
import com.vproject.texttoimage.core.designsystem.icon.TextToImageIcons
import com.vproject.texttoimage.core.designsystem.theme.GradientColors
import com.vproject.texttoimage.core.designsystem.theme.LocalGradientColors
import com.vproject.texttoimage.navigation.TextToImageNavHost
import com.vproject.texttoimage.navigation.TopLevelDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextToImageApp(
    windowSizeClass: WindowSizeClass,
    appState: TextToImageAppState = rememberTextToImageAppState(
        windowSizeClass = windowSizeClass,
    )
) {
    val shouldShowGradientBackground =
        appState.currentTopLevelDestination == TopLevelDestination.GENERATE
    TextToImageBackground {
        TextToImageGradientBackground(
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
                        TextToImageBottomBar(
                            destinations = appState.topLevelDestinations,
                            onNavigateToDestination = appState::navigateToTopLevelDestination,
                            currentDestination = appState.currentDestination,
                            modifier = Modifier.testTag("TextToImageBottomBar")
                        )
                    }
                }
            ) { paddingValues ->
                Column(Modifier.fillMaxSize()) {
                    // Show the top app bar on top level destinations.
                    val topLevelDestination = appState.currentTopLevelDestination
                    topLevelDestination?.let { nonNullTopLevelDestination ->
                        TextToImageTopAppBar(
                            titleRes = nonNullTopLevelDestination.titleTextId,
                            actionIcon = TextToImageIcons.RoundedSettings,
                            actionIconContentDescription = "settings",
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = Color.Transparent,
                            ),
                            onActionClick = { appState.navigateToSettings() }
                        )
                    }
                    TextToImageNavHost(appState = appState)
                }
            }
        }
    }
}

@Composable
private fun TextToImageBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    TextToImageNavigationBar(
        modifier = modifier,
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            TextToImageNavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
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