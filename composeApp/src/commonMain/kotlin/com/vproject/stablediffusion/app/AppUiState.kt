package com.vproject.stablediffusion.app

import com.vproject.stablediffusion.model.DarkThemeConfig

sealed class AppUiState {
    data object Initial : AppUiState()
    data class Success(val darkThemeConfig: DarkThemeConfig) : AppUiState()
}