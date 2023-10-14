package com.vproject.texttoimage.feature.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vproject.texttoimage.core.domain.GetGeneratedPromptListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class GalleryViewModel @Inject constructor(
    getGeneratedPromptListUseCase: GetGeneratedPromptListUseCase
) : ViewModel() {
    val galleryUiState: StateFlow<GalleryUiState> =
        getGeneratedPromptListUseCase().map(GalleryUiState::Success)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = GalleryUiState.Loading
            )
}