package com.vproject.stablediffusion.presentation.screen.generate

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import com.vproject.stablediffusion.SharedRes
import com.vproject.stablediffusion.model.StableDiffusionMode
import com.vproject.stablediffusion.presentation.component.CustomIcons
import com.vproject.stablediffusion.presentation.component.StableDiffusionTopBar
import com.vproject.stablediffusion.presentation.screen.detail.DetailScreen
import com.vproject.stablediffusion.presentation.screen.generate.tab.ImageToImageTab
import com.vproject.stablediffusion.presentation.screen.generate.tab.TextToImageTab
import dev.icerock.moko.resources.compose.painterResource

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
            onTextToImageDrawClicked = { prompt, styleId, canvasId ->
                navigator?.push(DetailScreen(stableDiffusionMode, null, prompt, styleId, canvasId))
            },
            onImageToImageDrawClicked = { selectedImageBitmap, prompt, styleId, canvasId ->
                navigator?.push(DetailScreen(stableDiffusionMode, selectedImageBitmap, prompt, styleId, canvasId))
            }
        )
    }
}

@Composable
private fun GenerateContent(
    uiState: GenerateUiState,
    stableDiffusionMode: StableDiffusionMode,
    onBackClicked: () -> Unit = {},
    onTextToImageDrawClicked: (prompt: String, styleId: String, canvasId: String) -> Unit = { _, _, _ -> },
    onImageToImageDrawClicked: (originalImage: ByteArray, prompt: String, styleId: String, canvasId: String) -> Unit = { _, _, _, _ -> }
) {
    var tabIndex by remember {
        mutableStateOf(
            StableDiffusionMode.entries.indexOf(
                stableDiffusionMode
            )
        )
    }
    val tabs = listOf("Text to Image", "Image to Image")

    Column(modifier = Modifier.fillMaxWidth()) {
        GenerateTopBar(onBackClicked = onBackClicked)
        TabRow(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
            selectedTabIndex = tabIndex
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(text = {
                    Text(
                        text = title,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    )
                },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> TextToImageTab(onTextToImageDrawClicked = onTextToImageDrawClicked)
            1 -> ImageToImageTab(onImageToImageDrawClicked = onImageToImageDrawClicked)
        }
    }
}

@Composable
private fun GenerateTopBar(
    onBackClicked: () -> Unit = {},
) {
    Box(
        modifier = Modifier.height(50.dp).fillMaxWidth(),
    ) {

        IconButton(
            modifier = Modifier.align(Alignment.CenterStart).padding(horizontal = 10.dp),
            onClick = onBackClicked
        ) {
            Icon(
                modifier = Modifier.size(18.dp),
                painter = painterResource(SharedRes.images.ic_back),
                contentDescription = "actionIconContentDescription",
                tint = MaterialTheme.colorScheme.onSurface,
            )
        }

        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Generate",
            style = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            ),
        )
    }
}