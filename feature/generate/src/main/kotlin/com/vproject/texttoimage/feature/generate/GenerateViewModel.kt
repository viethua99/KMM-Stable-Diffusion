package com.vproject.texttoimage.feature.generate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vproject.texttoimage.core.domain.GetFavorableStyleListUseCase
import com.vproject.texttoimage.core.domain.GetTopTrendingListUseCase
import com.vproject.texttoimage.core.domain.ToggleFavoriteStyleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class GenerateViewModel @Inject constructor(
    getFavorableStyleListUseCase: GetFavorableStyleListUseCase,
    getTopTrendingListUseCase: GetTopTrendingListUseCase,
    private val toggleFavoriteStyleUseCase: ToggleFavoriteStyleUseCase
) : ViewModel() {
    val generateUiState: StateFlow<GenerateUiState> =

        combine(
            getFavorableStyleListUseCase(),
            getTopTrendingListUseCase()
        ) { styleList, topTrendingList ->
            GenerateUiState.Success(styleList, topTrendingList)
        }
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
}