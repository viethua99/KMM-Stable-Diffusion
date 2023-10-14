package com.vproject.texttoimage.feature.gallery

import com.vproject.texttoimage.core.model.data.PromptData

internal sealed interface GalleryUiState {
    object Loading: GalleryUiState
    data class Success(val generatedPromptList: List<PromptData>): GalleryUiState
}