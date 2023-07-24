package com.vproject.brushai.feature.generate

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vproject.brushai.core.domain.GenerateImageUseCase
import com.vproject.brushai.core.domain.GetFavorableStyleListUseCase
import com.vproject.brushai.core.domain.ToggleFavoriteStyleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenerateViewModel @Inject constructor(
    getFavorableStyleListUseCase: GetFavorableStyleListUseCase,
    private val toggleFavoriteStyleUseCase: ToggleFavoriteStyleUseCase,
    private val generateImageUseCase: GenerateImageUseCase,
    ) : ViewModel() {
    val generateUiState: StateFlow<GenerateUiState> =
        getFavorableStyleListUseCase()
            .map(GenerateUiState::Success)
            .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = GenerateUiState.Loading
        )

    fun updateFavoriteStyle(styleId: String, isFavorite: Boolean) {
        viewModelScope.launch {
            toggleFavoriteStyleUseCase(styleId, isFavorite)
        }
    }

    fun generateImage(prompt: String) {
        viewModelScope.launch {
           val url = generateImageUseCase(prompt)
            Log.d("TEST", "Result = $url")
        }
    }
}