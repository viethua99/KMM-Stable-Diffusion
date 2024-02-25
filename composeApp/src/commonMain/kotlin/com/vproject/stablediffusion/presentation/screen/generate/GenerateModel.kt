package com.vproject.stablediffusion.presentation.screen.generate

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.vproject.stablediffusion.repository.image.ImageRepository
import com.vproject.stablediffusion.util.TestUtil
import com.vproject.stablediffusion.util.imageBitmapFromBytes
import kotlinx.coroutines.launch
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class GenerateModel(private val imageRepository: ImageRepository, private val testUtil: TestUtil) : StateScreenModel<GenerateUiState>(GenerateUiState.Initial) {
    @OptIn(ExperimentalEncodingApi::class)
    fun generateImage(prompt: String, styleId: String, canvasId: String) = screenModelScope.launch {
        imageRepository.generateImage(prompt, styleId, canvasId)
            .onSuccess { generatedImageInfo ->
                val decodedImage = Base64.Default.decode(generatedImageInfo.base64)
                val imageBitmap = imageBitmapFromBytes(decodedImage)
                mutableState.value = GenerateUiState.Success(imageBitmap)
            }
            .onFailure {
                mutableState.value = GenerateUiState.Error(it.message ?: "Unknown error")
            }
    }
}