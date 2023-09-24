package com.vproject.brushai.feature.loading

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class LoadingViewModel @Inject constructor() : ViewModel() {
    val loadingUiState: StateFlow<LoadingUiState> =
        flow<LoadingUiState> {}
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = LoadingUiState.Loading
            )
}