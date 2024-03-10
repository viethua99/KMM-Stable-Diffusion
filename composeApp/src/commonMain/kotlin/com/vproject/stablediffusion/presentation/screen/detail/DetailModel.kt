package com.vproject.stablediffusion.presentation.screen.detail

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.vproject.stablediffusion.model.CanvasPreset
import com.vproject.stablediffusion.model.StylePreset
import com.vproject.stablediffusion.repository.image.ImageRepository
import com.vproject.stablediffusion.util.imageBitmapFromBytes
import kotlinx.coroutines.launch

class DetailModel(private val imageRepository: ImageRepository) :
    StateScreenModel<DetailUiState>(DetailUiState.Initial) {
    fun generateTextToImage(prompt: String, styleId: String, canvasId: String) =
        screenModelScope.launch {
            mutableState.value = DetailUiState.Loading
            imageRepository.generateTextToImage(prompt, styleId, canvasId)
                .onSuccess { projectInfo ->
                    val imageBitmap = imageBitmapFromBytes(projectInfo.generatedImage)
                    mutableState.value = DetailUiState.Success(
                        "tti",
                        null,
                        imageBitmap,
                        prompt,
                        StylePreset.entries.find { it.id == styleId }?.displayName ?: "",
                        CanvasPreset.entries.find { it.id == canvasId }?.id ?: "",
                        CanvasPreset.entries.find { it.id == canvasId }?.aspectRatio ?: (1 / 1f),
                    )
                }
                .onFailure {
                    mutableState.value = DetailUiState.Error(it.message ?: "Unknown error")
                }
        }

    fun generateImageToImage(originalImageByteArray: ByteArray, prompt: String, styleId: String, canvasId: String) =
        screenModelScope.launch {
            mutableState.value = DetailUiState.Loading
            imageRepository.generateImageToImage(originalImageByteArray, prompt, styleId, canvasId)
                .onSuccess { projectInfo ->
                    if (projectInfo.originalImage != null) {
                        val originalImageBitmap = imageBitmapFromBytes(projectInfo.originalImage)
                        val generatedImageBitmap = imageBitmapFromBytes(projectInfo.generatedImage)
                        mutableState.value = DetailUiState.Success(
                            "iti",
                            originalImageBitmap,
                            generatedImageBitmap,
                            prompt,
                            StylePreset.entries.find { it.id == styleId }?.displayName ?: "",
                            CanvasPreset.entries.find { it.id == canvasId }?.id ?: "",
                            CanvasPreset.entries.find { it.id == canvasId }?.aspectRatio
                                ?: (1 / 1f),
                        )
                    }
                }
                .onFailure {
                    mutableState.value = DetailUiState.Error(it.message ?: "Unknown error")
                }
        }
}