package com.vproject.brushai.feature.generate

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GenerateViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(GenerateUiState.Loaded)
    val uiState: StateFlow<GenerateUiState> = _uiState.asStateFlow()
}