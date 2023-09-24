package com.vproject.texttoimage.feature.loading

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun LoadingRoute(
    modifier: Modifier = Modifier,
    viewModel: LoadingViewModel = hiltViewModel()
) {
    val loadingUiState by viewModel.loadingUiState.collectAsStateWithLifecycle()
    LoadingScreen(
        loadingUiState = loadingUiState,
        modifier = modifier.fillMaxSize()
    )
}

@Composable
internal fun LoadingScreen(
    modifier: Modifier = Modifier,
    loadingUiState: LoadingUiState,
) {

}