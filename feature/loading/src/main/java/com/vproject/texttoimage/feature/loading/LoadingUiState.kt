package com.vproject.texttoimage.feature.loading

sealed interface LoadingUiState {
    object Generating: LoadingUiState
    data class Generated(val url: String, val prompt: String, val styleId: String): LoadingUiState
}