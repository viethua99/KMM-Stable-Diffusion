package com.vproject.texttoimage.feature.generate

import com.vproject.texttoimage.core.model.data.FavorableStyle
import com.vproject.texttoimage.core.model.data.PromptData

internal sealed interface GenerateUiState {
    object Loading: GenerateUiState
    data class Success(val styles: List<FavorableStyle>, val topTrendingList: List<PromptData>): GenerateUiState
}