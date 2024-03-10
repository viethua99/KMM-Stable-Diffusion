package com.vproject.stablediffusion.presentation.screen.home

import com.vproject.stablediffusion.model.DarkThemeConfig

sealed class HomeUiState {
    data object Initial : HomeUiState()
    data class Success(
        val darkThemeConfig: DarkThemeConfig,
    ) : HomeUiState()
}