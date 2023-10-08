package com.vproject.texttoimage.feature.result

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vproject.texttoimage.core.domain.GetFavorableStyleUseCase
import com.vproject.texttoimage.feature.result.navigation.ResultArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getFavorableStyleUseCase: GetFavorableStyleUseCase
) : ViewModel() {
    private val resultArgs = ResultArgs(savedStateHandle)

    val resultUiState: StateFlow<ResultUiState> =
        getFavorableStyleUseCase(resultArgs.styleId).map { favorableStyle ->
            ResultUiState.ShowResult(
                resultArgs.imageUrl,
                resultArgs.prompt,
                favorableStyle.style.name
            )
        }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = ResultUiState.Empty
            )
}