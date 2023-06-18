package com.vproject.brushai.feature.prompt

sealed interface PromptUiState  {
    object Loading: PromptUiState
    object Loaded: PromptUiState
}