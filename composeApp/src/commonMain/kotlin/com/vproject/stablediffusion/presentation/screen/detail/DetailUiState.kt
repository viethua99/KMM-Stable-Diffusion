package com.vproject.stablediffusion.presentation.screen.detail

import androidx.compose.ui.graphics.ImageBitmap

sealed class DetailUiState {
    data object Initial : DetailUiState()
    data object Loading : DetailUiState()
    data class Success(val imageBitmap: ImageBitmap, val prompt: String, val styleName: String, val canvasName: String) : DetailUiState()
    data class Error(val message: String) : DetailUiState()
}