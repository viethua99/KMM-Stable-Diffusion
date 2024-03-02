package com.vproject.stablediffusion.presentation.screen.project

import com.vproject.stablediffusion.model.TestSample

sealed class ProjectUiState {
    data object Initial : ProjectUiState()
    data object Loading : ProjectUiState()
    data class Success(val projectList: List<TestSample>) : ProjectUiState()
    data class Error(val message: String) : ProjectUiState()
}