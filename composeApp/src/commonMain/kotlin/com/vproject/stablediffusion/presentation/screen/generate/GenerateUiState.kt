package com.vproject.stablediffusion.presentation.screen.generate

import androidx.compose.ui.graphics.ImageBitmap

sealed class GenerateUiState {
    data object Initial : GenerateUiState()
    data object Loading : GenerateUiState()
    data class Success(val imageBitmap: ImageBitmap) : GenerateUiState()
    data class Error(val message: String) : GenerateUiState()
}