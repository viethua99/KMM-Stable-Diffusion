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
import com.vproject.stablediffusion.presentation.screen.generate.tab.ImageToImageTab
import com.vproject.stablediffusion.presentation.screen.generate.tab.TextToImageTab

data class GenerateScreen(val stableDiffusionMode: StableDiffusionMode) : Screen {
    @Composable
    override fun Content() {
        val screenModel: GenerateModel = getScreenModel()
        val uiState by screenModel.state.collectAsState()
        val navigator = LocalNavigator.current

        GenerateContent(uiState,
            onBackClicked = {
                navigator?.pop()
            },
            onDrawingClicked = {
                screenModel.generateImage("Helo", "World")
            }
        )
    }
}

@Composable
private fun GenerateContent(
    uiState: GenerateUiState,
    onBackClicked: () -> Unit = {},
    onDrawingClicked: () -> Unit = {},
) {
    var tabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Txt to Img", "Img to Img")

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
            0 -> TextToImageTab(onDrawingClicked = onDrawingClicked)
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