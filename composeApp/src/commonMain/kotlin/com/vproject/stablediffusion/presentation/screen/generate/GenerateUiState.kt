package com.vproject.stablediffusion.presentation.screen.generate

sealed class GenerateUiState {
    data object Initial : GenerateUiState()
    data object Loading : GenerateUiState()
    data class Success(val imageUrl: String) : GenerateUiState()
    data class Error(val message: String) : GenerateUiState()
}