package com.vproject.texttoimage.feature.generate

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vproject.texttoimage.core.domain.GenerateImageUseCase
import com.vproject.texttoimage.core.domain.GetFavorableStyleListUseCase
import com.vproject.texttoimage.core.domain.ToggleFavoriteStyleUseCase
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

    fun generateImage(prompt: String, selectedStyleId: String) {
        viewModelScope.launch {
            val flow = generateImageUseCase(prompt, selectedStyleId)
            flow.collect { output ->
                Log.d("TEST", "Result = $output")
            }
        }
    }
}