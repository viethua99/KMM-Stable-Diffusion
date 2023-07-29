package com.vproject.brushai.feature.settings

import com.vproject.brushai.core.model.data.UserEditableSettings

sealed interface SettingsUiState {
    object Loading: SettingsUiState
    data class Success(val settings: UserEditableSettings) : SettingsUiState
}