package com.vproject.brushai.feature.generate

import com.vproject.brushai.core.model.data.FavorableStyle

sealed interface GenerateUiState {
    object Loading: GenerateUiState
    data class Success(val styles: List<FavorableStyle>): GenerateUiState
}