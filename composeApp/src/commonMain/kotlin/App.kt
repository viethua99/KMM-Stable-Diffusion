import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.vproject.stablediffusion.presentation.component.CustomIcons
import com.vproject.stablediffusion.presentation.component.StableDiffusionAppBackground
import com.vproject.stablediffusion.presentation.component.StableDiffusionNavigationBar
import com.vproject.stablediffusion.presentation.component.StableDiffusionNavigationBarItem
import com.vproject.stablediffusion.presentation.component.StableDiffusionTopBar
import com.vproject.stablediffusion.presentation.component.theme.StableDiffusionAppTheme
import com.vproject.stablediffusion.presentation.screen.gallery.GalleryTab
import com.vproject.stablediffusion.presentation.screen.generate.GenerateTab
import com.vproject.stablediffusion.presentation.screen.setting.SettingScreen

@Composable
fun App() {
    StableDiffusionAppTheme(darkTheme = true) {
        StableDiffusionAppBackground {
            Navigator(MainContainer())
        }
    }
}

private class MainContainer : Screen {
    @OptIn(ExperimentalVoyagerApi::class, ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        TabNavigator(
            GenerateTab, tabDisposable = {
                TabDisposable(
                    navigator = it,
                    tabs = listOf(GenerateTab, GalleryTab)
                )
            }
        ) { tabNavigator ->
            Scaffold(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onBackground,
                contentWindowInsets = WindowInsets(0, 0, 0, 0),
                topBar = {
                    StableDiffusionTopBar(
                        title =  tabNavigator.current.options.title,
                        actionIcon = CustomIcons.RoundedSettings,
                        actionIconContentDescription = "settings",
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = Color.Transparent,
                        ),
                        onActionClick = {
                            navigator?.push(SettingScreen)
                        }
                    )
                },
                bottomBar = {
                    StableDiffusionBottomBar(
                        tabs = listOf(
                            GenerateTab,
                            GalleryTab
                        ),
                    )
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
        modifier = modifier,
    ) {
        tabs.forEach { tab ->
            val tabNavigator = LocalTabNavigator.current
            val selected = tabNavigator.current.key == tab.key
            StableDiffusionNavigationBarItem(
                selected = selected,
                onClick = {tabNavigator.current = tab  },
                icon = {
                    Icon(painter = tab.options.icon!!, contentDescription = tab.options.title)

                },
                label = { Text(tab.options.title) })
        }
    }
}