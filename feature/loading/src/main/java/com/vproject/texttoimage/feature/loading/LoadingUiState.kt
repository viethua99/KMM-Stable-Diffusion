package com.vproject.texttoimage.feature.loading

sealed interface LoadingUiState {
    object Loading: LoadingUiState
}