package com.vproject.texttoimage.feature.gallery

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vproject.texttoimage.core.designsystem.component.DynamicAsyncImage
import com.vproject.texttoimage.core.model.data.PromptData

@Composable
internal fun GalleryRoute(
    viewModel: GalleryViewModel = hiltViewModel(),
    onPromptItemClick: (promptContent: String, imageUrl: String) -> Unit
) {
    val galleryUiState by viewModel.galleryUiState.collectAsStateWithLifecycle()

    GalleryScreen(galleryUiState = galleryUiState, onPromptItemClick = onPromptItemClick)
}

@Composable
internal fun GalleryScreen(
    modifier: Modifier = Modifier,
    galleryUiState: GalleryUiState,
    onPromptItemClick: (promptContent: String, imageUrl: String) -> Unit
) {
    Column {
        if (galleryUiState is GalleryUiState.Success) {
            LazyVerticalGrid(
                modifier = modifier.padding(start = 10.dp, end = 10.dp),
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                item(span = { GridItemSpan(2) }) {
                    Text(
                        text = "Generated Image",
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        ),
                    )
                    Spacer(Modifier.height(10.dp))
                }

                items(galleryUiState.generatedPromptList) { promptData ->
                    GeneratedPromptItem(
                        promptData = promptData,
                        onItemClick = onPromptItemClick
                    )
                }
            }
        }
    }
}

@Composable
private fun GeneratedPromptItem(
    modifier: Modifier = Modifier,
    promptData: PromptData,
    onItemClick: (promptContent: String, imageUrl: String) -> Unit
) {
    Box(
        modifier
            .fillMaxWidth()
            .height(230.dp)
            .clickable {
                onItemClick(promptData.content, promptData.imageUrl ?: "")
            }
    ) {
        DynamicAsyncImage(
            imageUrl = promptData.imageUrl ?: "",
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(MaterialTheme.shapes.medium),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(42.dp)
                .padding(start = 5.dp, end = 5.dp, bottom = 5.dp)
                .align(Alignment.BottomEnd)
        ) {
            Text(
                style = TextStyle(
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                ),
                modifier = Modifier.fillMaxWidth(),
                text = promptData.content,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}