package com.vproject.stablediffusion.presentation.screen.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import com.vproject.stablediffusion.SharedRes
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.compose.painterResource

class DetailScreen(
    private val prompt: String,
    private val styleId: String,
    private val canvasId: String
) : Screen {
    @Composable
    override fun Content() {
        val screenModel: DetailModel = getScreenModel()
        val detailUiState by screenModel.state.collectAsState()
        val localNavigator = LocalNavigator.current

        LaunchedEffect(Unit) {
            screenModel.generateImage(prompt, styleId, canvasId)
        }

        DetailContent(
            detailUiState,
            onBackClicked = {
                localNavigator?.pop()
            }
        )
    }
}

@Composable
private fun DetailContent(
    detailUiState: DetailUiState,
    onBackClicked: () -> Unit = {},
    ) {
    when (detailUiState) {
        DetailUiState.Initial -> {
            // TODO
        }

        DetailUiState.Loading -> {
            LoadingContent()
        }

        is DetailUiState.Success -> {
            ResultContent(detailUiState, onBackClicked = onBackClicked)
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
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.primary
            )
        }
        Text("Generating...")
    }
}

@Composable
private fun ResultContent(
    uiState: DetailUiState.Success,
    onBackClicked: () -> Unit = {},
) {
    Column(modifier = Modifier) {
        DetailTopBar(onBackClicked = onBackClicked)
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 40.dp)
            ) {
                Column(
                    Modifier.weight(1f).verticalScroll(
                        rememberScrollState()
                    )
                ) {
                    ResultImage(uiState.imageBitmap)
                    Spacer(modifier = Modifier.height(10.dp))
                    SectionHeader("Prompt", leadingIcon = {
                        Icon(
                            modifier = Modifier.size(12.dp),
                            tint = MaterialTheme.colorScheme.onSurface,
                            painter = painterResource(SharedRes.images.ic_star),
                            contentDescription = null
                        )
                    })
                    Spacer(modifier = Modifier.height(5.dp))
                    PromptCard(uiState.prompt)

                    Spacer(modifier = Modifier.height(10.dp))
                    SectionHeader("Information", leadingIcon = {
                        Icon(
                            modifier = Modifier.size(12.dp),
                            tint = MaterialTheme.colorScheme.onSurface,
                            painter = painterResource(SharedRes.images.ic_star),
                            contentDescription = null
                        )
                    })
                    Spacer(modifier = Modifier.height(5.dp))
                    InformationCard(uiState)
                    Spacer(Modifier.height(60.dp))
                }
            }

            ActionButtonRow(
                modifier = Modifier.align(Alignment.BottomCenter)
                    .padding(start = 30.dp, end = 30.dp, bottom = 30.dp),
                enabled = false,
                onClick = { }
            )
        }
    }
}

@Composable
private fun DetailTopBar(
    modifier: Modifier = Modifier,
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
            text = "Detail",
            style = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            ),
        )
    }
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
//    BeforeAfterImage(
//        enableZoom = false,
//        modifier = Modifier
//            .clip(RoundedCornerShape(10.dp))
//            .fillMaxWidth()
//            .aspectRatio(1 / 1f),
//        beforeImage = imageBitMap,
//        afterImage = test,
//    )
}

@Composable
private fun SectionHeader(
    title: String,
    leadingIcon: @Composable (() -> Unit)? = null
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        leadingIcon?.let { nonNullLeadingIcon ->
            nonNullLeadingIcon()
        }

        Box(Modifier.padding(start = 4.dp)) {
            Text(
                title,
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Light,
                    fontSize = 10.sp
                ),
            )
        }

        Spacer(
            Modifier.weight(1f).fillMaxHeight()
        )
        Icon(
            modifier = Modifier.size(14.dp),
            tint = MaterialTheme.colorScheme.onSurface,
            painter = painterResource(SharedRes.images.ic_copy),
            contentDescription = null
        )

    }
}

@Composable
private fun InformationCard(uiState: DetailUiState.Success) {
    Card(
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            1.dp, color = MaterialTheme.colorScheme.surface
        ),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        modifier = Modifier.fillMaxWidth()

    ) {
        Column(Modifier.padding(vertical = 4.dp, horizontal = 4.dp)) {
            InformationItem(
                title = "Style",
                content = uiState.styleName,
            )
            InformationItem(
                title = "Canvas",
                content = uiState.canvasName,
            )

        }
    }
}

@Composable
private fun PromptCard(content: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.surface),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, start = 8.dp, end = 6.dp),
            text = content,
            style = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Light,
                fontSize = 10.sp
            ),
        )
    }
}

@Composable
private fun InformationItem(
    title: String,
    content: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            title,
            style = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Normal,
                fontSize = 10.sp
            ),
            modifier = Modifier.weight(1f)
        )

        Text(
            text = content,
            style = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Light,
                fontSize = 10.sp
            ),
            textAlign = TextAlign.End,
        )
    }
}

@Composable
private fun ActionButtonRow(modifier: Modifier = Modifier, enabled: Boolean, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = modifier.wrapContentWidth()

    ) {
        Row(
            modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            ActionButton("Save", SharedRes.images.ic_save, onClick = onClick)
            ActionButton("Share", SharedRes.images.ic_share, onClick = onClick)
        }
    }
}

@Composable
private fun ActionButton(title: String, icon: ImageResource, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource(icon),
            contentDescription = title,
        )
        Text(
            title,
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 10.sp,
            ),
        )
    }
}