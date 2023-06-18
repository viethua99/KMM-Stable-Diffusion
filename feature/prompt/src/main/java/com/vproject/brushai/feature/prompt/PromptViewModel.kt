package com.vproject.brushai.feature.prompt

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PromptViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(PromptUiState.Loaded)
    val uiState: StateFlow<PromptUiState> = _uiState.asStateFlow()
}