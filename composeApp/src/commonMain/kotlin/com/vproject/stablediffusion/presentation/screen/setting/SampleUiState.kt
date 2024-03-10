package com.vproject.stablediffusion.presentation.screen.sample

import androidx.compose.ui.graphics.ImageBitmap
import dev.icerock.moko.resources.ImageResource

sealed class SampleUiState {
    data object Initial : SampleUiState()
    data class TextToImageLoaded(
        val generatedImageResource: ImageResource,
        val prompt: String,
        val styleName: String,
        val canvasName: String,
        val aspectRatio: Float
    ): SampleUiState()

    data class ImageToImageLoaded(
        val originalImageResource: ImageResource,
        val generatedImageResource: ImageResource,
        val prompt: String,
        val styleName: String,
        val canvasName: String,
        val aspectRatio: Float
    ): SampleUiState()
}