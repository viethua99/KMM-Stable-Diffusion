package com.vproject.stablediffusion.presentation.screen.generate

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.vproject.stablediffusion.repository.ImageRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class GenerateModel(imageRepository: ImageRepository) : ScreenModel {
    val objects: StateFlow<String> =
        imageRepository.generateImage("Helo","World")
            .stateIn(screenModelScope, SharingStarted.WhileSubscribed(5000), "")
}