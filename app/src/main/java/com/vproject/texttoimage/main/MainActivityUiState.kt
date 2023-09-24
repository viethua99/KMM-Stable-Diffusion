package com.vproject.texttoimage.main

import com.vproject.texttoimage.core.model.data.DarkThemeConfig

sealed interface MainActivityUiState {
    object Loading : MainActivityUiState
    data class Success(val darkThemeConfig: DarkThemeConfig) : MainActivityUiState
}