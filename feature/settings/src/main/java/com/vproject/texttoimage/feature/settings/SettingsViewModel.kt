package com.vproject.texttoimage.feature.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vproject.texttoimage.core.domain.GetUserEditableSettingsUseCase
import com.vproject.texttoimage.core.domain.SetDarkThemeConfigUseCase
import com.vproject.texttoimage.core.domain.SetPromptCfgScaleValueUseCase
import com.vproject.texttoimage.core.domain.SetPromptStepValueUseCase
import com.vproject.texttoimage.core.model.data.DarkThemeConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    getUserEditableSettingsUseCase: GetUserEditableSettingsUseCase,
    private val setPromptCfgScaleValueUseCase: SetPromptCfgScaleValueUseCase,
    private val setPromptStepValueUseCase: SetPromptStepValueUseCase,
    private val setDarkThemeConfigUseCase: SetDarkThemeConfigUseCase
) : ViewModel() {
    val settingsUiState: StateFlow<SettingsUiState> =
        getUserEditableSettingsUseCase().map { userEditableSettings ->
            SettingsUiState.Success(settings = userEditableSettings)
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = SettingsUiState.Loading
            )

    fun updatePromptCfgScaleValue(promptCfgScaleValue: Float) {
        viewModelScope.launch {
            setPromptCfgScaleValueUseCase(promptCfgScaleValue)
        }
    }

    fun updatePromptStepValue(promptStepValue: Float) {
        viewModelScope.launch {
            setPromptStepValueUseCase(promptStepValue)
        }
    }

    fun updateDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        viewModelScope.launch {
            setDarkThemeConfigUseCase(darkThemeConfig)
        }
    }
}