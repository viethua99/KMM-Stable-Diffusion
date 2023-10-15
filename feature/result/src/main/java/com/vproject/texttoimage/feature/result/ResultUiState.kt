package com.vproject.texttoimage.feature.result

internal sealed interface ResultUiState {
    object Empty: ResultUiState
    data class ShowResult(val url: String, val prompt: String, val style: String): ResultUiState
}