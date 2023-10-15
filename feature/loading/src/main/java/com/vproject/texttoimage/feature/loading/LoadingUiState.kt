package com.vproject.texttoimage.feature.loading

internal sealed interface LoadingUiState {
    object Generating: LoadingUiState
    object Error: LoadingUiState
    data class Generated(val url: String, val prompt: String, val styleId: String): LoadingUiState
}