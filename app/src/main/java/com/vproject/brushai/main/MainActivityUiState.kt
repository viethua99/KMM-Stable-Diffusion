package com.vproject.brushai.main

import com.vproject.brushai.core.model.data.DarkThemeConfig

sealed interface MainActivityUiState {
    object Loading : MainActivityUiState
    data class Success(val darkThemeConfig: DarkThemeConfig) : MainActivityUiState
}