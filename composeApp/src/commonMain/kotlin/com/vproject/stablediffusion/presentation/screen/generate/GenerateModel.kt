package com.vproject.stablediffusion.presentation.screen.generate

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import cafe.adriel.voyager.core.model.screenModelScope
import com.vproject.stablediffusion.repository.ImageRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GenerateModel(private val imageRepository: ImageRepository) : StateScreenModel<GenerateUiState>(GenerateUiState.Initial) {
    fun generateImage(prompt: String, negativePrompt: String) = screenModelScope.launch {
        print("generateImage")
        imageRepository.generateImage(prompt, negativePrompt).collect {
            val data = it
            print("hello world = $data")
        }
    }
}