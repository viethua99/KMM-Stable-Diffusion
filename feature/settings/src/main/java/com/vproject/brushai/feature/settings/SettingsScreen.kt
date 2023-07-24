package com.vproject.brushai.feature.settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun SettingsRoute(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val settingsUiState by viewModel.settingsUiState.collectAsStateWithLifecycle()
    SettingsScreen(
        settingsUiState = settingsUiState,
        modifier = modifier.fillMaxSize()
    )
}

@Composable
internal fun SettingsScreen(
    settingsUiState: SettingsUiState,
    modifier: Modifier = Modifier,
) {

}
