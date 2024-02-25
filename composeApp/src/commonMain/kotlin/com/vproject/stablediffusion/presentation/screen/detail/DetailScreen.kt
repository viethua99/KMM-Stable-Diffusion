package com.vproject.stablediffusion.presentation.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import com.vproject.stablediffusion.presentation.component.AsyncImage
import com.vproject.stablediffusion.presentation.component.CustomFilledButton
import com.vproject.stablediffusion.presentation.component.CustomIcons
import com.vproject.stablediffusion.presentation.component.StableDiffusionTopBar
import dev.icerock.moko.resources.compose.painterResource

class DetailScreen(private val prompt: String, private val styleId: String, private val canvasId: String) : Screen {
    @Composable
    override fun Content() {
        val screenModel: DetailModel = getScreenModel()
        val detailUiState by screenModel.state.collectAsState()
        val localNavigator = LocalNavigator.current

        LaunchedEffect(Unit) {
            screenModel.generateImage(prompt, styleId, canvasId)
        }

        DetailContent(
            detailUiState
        )
    }
}

@Composable
private fun DetailContent(
    detailUiState: DetailUiState,
) {
    when (detailUiState) {
        DetailUiState.Initial -> {
            // TODO
        }
        DetailUiState.Loading -> {
            LoadingContent()
        }
        is DetailUiState.Success -> {
            ResultContent(detailUiState)
        }
        is DetailUiState.Error -> {
            Text(detailUiState.message)
        }
    }
}

@Composable
private fun LoadingContent(modifier: Modifier = Modifier, onBackClick: () -> Unit = {}) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.size(25.dp)) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.primary)
        }
        Text("Generating...")
    }
}

@Composable
private fun ResultContent(uiState: DetailUiState.Success, onBackClick: () -> Unit = {}) {
    Column(modifier = Modifier) {
        ResultTopAppBar(onBackClick = {})
        Column(
            Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 40.dp)
        ) {
            Column(Modifier.weight(1f)) {
                ResultImage(uiState.imageBitmap)
                ResultStyleRow(text = uiState.styleName)
                ResultPromptRow(content = uiState.prompt)
            }
//            ResultButtonRow(onDownloadClick = {
//
//            })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ResultTopAppBar(modifier: Modifier = Modifier, onBackClick: () -> Unit = {}) {
    StableDiffusionTopBar(
        modifier = modifier,
        title = "Result",
    )
}

@Composable
private fun ResultImage(imageBitMap: ImageBitmap) {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(450.dp)
            .clip(shape = MaterialTheme.shapes.medium),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        bitmap = imageBitMap
    )
}

@Composable
private fun ResultStyleRow(
    modifier: Modifier = Modifier,
    text: String,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Style:",
            style = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        )

        Text(
            text,
            textAlign = TextAlign.End,
            style = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            ),
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
    }
}

@Composable
private fun ResultPromptRow(modifier: Modifier = Modifier, content: String) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Prompt:", style = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            ), modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = CustomIcons.Home,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.width(8.dp))
    }
    Text(
        overflow = TextOverflow.Ellipsis,
        text = content,
        style = TextStyle(
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
    )
}

@Composable
private fun ResultButtonRow(
    modifier: Modifier = Modifier,
    onDownloadClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomFilledButton(
            modifier = Modifier.weight(1f),
            enabled = false,
            text = { Text(text = "Share") }, onClick = { /*TODO*/ },
            leadingIcon = {
                Icon(imageVector = CustomIcons.Home, contentDescription = null)
            },
        )
        Spacer(modifier = Modifier.width(10.dp))
        CustomFilledButton(
            modifier = Modifier.weight(1f),
            text = { Text(text = "Download") }, onClick = {
                onDownloadClick()
            },
            leadingIcon = {
                Icon(imageVector = CustomIcons.Home, contentDescription = null)
            },
        )
    }
}