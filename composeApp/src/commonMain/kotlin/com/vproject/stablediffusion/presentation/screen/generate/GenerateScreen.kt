package com.vproject.stablediffusion.presentation.screen.generate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import com.vproject.stablediffusion.model.StableDiffusionMode
import com.vproject.stablediffusion.presentation.component.CustomIcons
import com.vproject.stablediffusion.presentation.component.StableDiffusionTopBar
import com.vproject.stablediffusion.presentation.screen.detail.DetailScreen
import com.vproject.stablediffusion.presentation.screen.generate.tab.ImageToImageTab
import com.vproject.stablediffusion.presentation.screen.generate.tab.TextToImageTab

data class GenerateScreen(val stableDiffusionMode: StableDiffusionMode) : Screen {
    @Composable
    override fun Content() {
        val screenModel: GenerateModel = getScreenModel()
        val uiState by screenModel.state.collectAsState()
        val navigator = LocalNavigator.current

        GenerateContent(uiState, stableDiffusionMode,
            onBackClicked = {
                navigator?.pop()
            },
            onDrawClicked = { prompt, styleId, canvasId ->
                navigator?.push(DetailScreen(prompt, styleId, canvasId))
            }
        )
    }
}

@Composable
private fun GenerateContent(
    uiState: GenerateUiState,
    stableDiffusionMode: StableDiffusionMode,
    onBackClicked: () -> Unit = {},
    onDrawClicked: (prompt: String, styleId: String, canvasId: String) -> Unit = {_ , _, _ -> }
) {
    var tabIndex by remember { mutableStateOf(StableDiffusionMode.entries.indexOf(stableDiffusionMode)) }
    val tabs = listOf("Text to Image", "Image to Image")

    Column(modifier = Modifier.fillMaxWidth()) {
        GenerateTopBar(onBackClicked = onBackClicked)
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> TextToImageTab(onDrawClicked = onDrawClicked)
            1 -> ImageToImageTab()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GenerateTopBar(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit = {},
) {
    StableDiffusionTopBar(
        modifier = modifier,
        title = "Generate",
        navigationIcon = CustomIcons.ArrowBack,
        navigationIconContentDescription = "Back Icon",
        onNavigationClicked = onBackClicked,
    )
}