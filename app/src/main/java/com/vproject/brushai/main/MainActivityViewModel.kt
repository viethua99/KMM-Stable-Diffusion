package com.vproject.brushai.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vproject.brushai.core.domain.GetUserEditableSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    getUserEditableSettingsUseCase: GetUserEditableSettingsUseCase,
    ) : ViewModel() {
    val uiState: StateFlow<MainActivityUiState> = getUserEditableSettingsUseCase().map { userEditableSettings ->
        MainActivityUiState.Success(userEditableSettings.darkThemeConfig)
    }.stateIn(
        scope = viewModelScope,
        initialValue = MainActivityUiState.Loading,
        started = SharingStarted.WhileSubscribed(5_000),
    )
}