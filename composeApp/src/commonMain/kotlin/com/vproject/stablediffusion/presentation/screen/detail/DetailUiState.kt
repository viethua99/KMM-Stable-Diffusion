package com.vproject.stablediffusion.presentation.screen.detail

import androidx.compose.ui.graphics.ImageBitmap

sealed class DetailUiState {
    data object Initial : DetailUiState()
    data object Loading : DetailUiState()
    data class Success(
        val projectType: String,
        val originalImageBitmap: ImageBitmap? = null,
        val generatedImageBitmap: ImageBitmap,
        val prompt: String,
        val styleName: String,
        val canvasName: String,
        val aspectRatio: Float
    ) : DetailUiState()

    data class Error(val message: String) : DetailUiState()
}