package com.vproject.stablediffusion.app

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.vproject.stablediffusion.model.DarkThemeConfig
import com.vproject.stablediffusion.presentation.component.StableDiffusionAppBackground
import com.vproject.stablediffusion.presentation.component.StableDiffusionNavigationBar
import com.vproject.stablediffusion.presentation.component.StableDiffusionNavigationBarItem
import com.vproject.stablediffusion.presentation.component.theme.StableDiffusionAppTheme
import com.vproject.stablediffusion.presentation.screen.detail.DetailModel
import com.vproject.stablediffusion.presentation.screen.project.ProjectTab
import com.vproject.stablediffusion.presentation.screen.home.HomeTab

@Composable
fun App() {
    Navigator(AppContainer())
}

private class AppContainer: Screen {
    @Composable
    override fun Content() {
        val screenModel: AppModel = getScreenModel()
        val appUiState by screenModel.state.collectAsState()

        LaunchedEffect(Unit) {
            screenModel.getUserData()
        }

        val isUseDarkTheme = shouldUseDarkTheme(appUiState)
        StableDiffusionAppTheme(darkTheme = isUseDarkTheme) {
            StableDiffusionAppBackground {
                Navigator(MainTabContainer()) { navigator ->
                    SlideTransition(navigator)
                }
            }
        }
    }
}

private class MainTabContainer : Screen {
    val tabs = listOf(HomeTab, ProjectTab)

    @OptIn(ExperimentalVoyagerApi::class)
    @Composable
    override fun Content() {
        TabNavigator(
            tabs[0], tabDisposable = {
                TabDisposable(
                    navigator = it,
                    tabs = tabs
                )
            }
        ) {
            Scaffold(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onBackground,
                contentWindowInsets = WindowInsets(0, 0, 0, 0),
                bottomBar = {
                    StableDiffusionBottomBar(tabs = tabs)
                }
            ) { paddingValues ->
                Column(Modifier.fillMaxSize().padding(paddingValues)) {
                    CurrentTab()
                }
            }
        }
    }
}

/**
 * Bottom bar with list of tabs to select
 */
@Composable
private fun StableDiffusionBottomBar(
    modifier: Modifier = Modifier,
    tabs: List<Tab>
) {
    StableDiffusionNavigationBar(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
    ) {
        tabs.forEach { tab ->
            val tabNavigator = LocalTabNavigator.current
            val selected = tabNavigator.current.key == tab.key
            StableDiffusionNavigationBarItem(
                title = tab.options.title,
                selected = selected,
                onClick = { tabNavigator.current = tab },
                icon = tab.options.icon!!)
        }
    }
}

/**
 * Returns `true` if dark theme should be used, as a function of the [uiState] and the
 * current system context.
 */
@Composable
private fun shouldUseDarkTheme(
    uiState: AppUiState,
): Boolean = when (uiState) {
    AppUiState.Initial -> isSystemInDarkTheme()
    is AppUiState.Success -> when (uiState.darkThemeConfig) {
        DarkThemeConfig.LIGHT -> false
        DarkThemeConfig.DARK -> true
    }
}