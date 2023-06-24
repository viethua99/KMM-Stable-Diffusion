package com.vproject.brushai.feature.generate

sealed interface GenerateUiState  {
    object Loading: GenerateUiState
    object Loaded: GenerateUiState
}