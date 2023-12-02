import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.vproject.stablediffusion.presentation.component.StableDiffusionAppBackground
import com.vproject.stablediffusion.presentation.component.StableDiffusionNavigationBar
import com.vproject.stablediffusion.presentation.component.StableDiffusionNavigationBarItem
import com.vproject.stablediffusion.presentation.component.theme.StableDiffusionAppTheme
import com.vproject.stablediffusion.presentation.screen.recent.RecentTab
import com.vproject.stablediffusion.presentation.screen.home.HomeTab

@OptIn(ExperimentalVoyagerApi::class)
@Composable
fun App() {
    val tabs = listOf(HomeTab, RecentTab)

    StableDiffusionAppTheme(darkTheme = true) {
        StableDiffusionAppBackground {
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
                onClick = { tabNavigator.current = tab  },
                icon = {
                    Icon(painter = tab.options.icon!!, contentDescription = tab.options.title)

                },
                label = { Text(tab.options.title) })
        }
    }
}