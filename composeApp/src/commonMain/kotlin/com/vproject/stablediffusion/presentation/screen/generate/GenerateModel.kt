package com.vproject.stablediffusion.presentation.screen.generate

import cafe.adriel.voyager.core.model.StateScreenModel
import com.vproject.stablediffusion.repository.image.ImageRepository

class GenerateModel(private val imageRepository: ImageRepository) : StateScreenModel<GenerateUiState>(GenerateUiState.Initial) {

}