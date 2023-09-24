package com.vproject.texttoimage.feature.generate

import com.vproject.texttoimage.core.model.data.FavorableStyle

sealed interface GenerateUiState {
    object Loading: GenerateUiState
    data class Success(val styles: List<FavorableStyle>): GenerateUiState
}