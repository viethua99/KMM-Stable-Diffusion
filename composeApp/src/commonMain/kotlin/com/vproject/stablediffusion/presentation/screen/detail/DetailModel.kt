package com.vproject.stablediffusion.presentation.screen.detail

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.vproject.stablediffusion.model.CanvasPreset
import com.vproject.stablediffusion.model.StylePreset
import com.vproject.stablediffusion.repository.image.ImageRepository
import com.vproject.stablediffusion.util.imageBitmapFromBytes
import kotlinx.coroutines.launch
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class DetailModel(private val imageRepository: ImageRepository) :
    StateScreenModel<DetailUiState>(DetailUiState.Initial) {
    @OptIn(ExperimentalEncodingApi::class)
    fun generateImage(prompt: String, styleId: String, canvasId: String) = screenModelScope.launch {
        mutableState.value = DetailUiState.Loading
        imageRepository.generateImage(prompt, styleId, canvasId)
            .onSuccess { generatedImageInfo ->
                val decodedImage = Base64.Default.decode(generatedImageInfo.base64)
                val imageBitmap = imageBitmapFromBytes(decodedImage)
                mutableState.value = DetailUiState.Success(
                    imageBitmap,
                    prompt,
                    StylePreset.entries.find { it.id == styleId }?.displayName ?: "",
                    CanvasPreset.entries.find { it.id == canvasId }?.name ?: "",
                    )
            }
            .onFailure {
                mutableState.value = DetailUiState.Error(it.message ?: "Unknown error")
            }
    }
}