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
            val stylePreset = StylePreset.entries.find { it.id == styleId }
            val canvasPreset = CanvasPreset.entries.find { it.id == canvasId }

            if (stylePreset != null && canvasPreset != null) {
                mutableState.value = DetailUiState.Loading
                imageRepository.generateTextToImage(
                    prompt,
                    styleId,
                    canvasPreset.width,
                    canvasPreset.height
                )
                    .onSuccess { projectInfo ->
                        val imageBitmap = imageBitmapFromBytes(projectInfo.generatedImage)
                        mutableState.value = DetailUiState.Success(
                            projectType = projectInfo.projectType,
                            generatedImageBitmap = imageBitmap,
                            prompt = prompt,
                            styleName = stylePreset.displayName,
                            canvasName = canvasPreset.id,
                            aspectRatio = canvasPreset.aspectRatio,
                        )
                    }
                    .onFailure {
                        mutableState.value = DetailUiState.Error(it.message ?: "Unknown error")
                    }
            } else {
                mutableState.value = DetailUiState.Error("Unsupported Style or Canvas")
            }
        }

    fun generateImageToImage(
        originalImageByteArray: ByteArray,
        imageStrength: Double,
        prompt: String,
        styleId: String,
        canvasId: String
    ) =
        screenModelScope.launch {
            val stylePreset = StylePreset.entries.find { it.id == styleId }
            val canvasPreset = CanvasPreset.entries.find { it.id == canvasId }
            if (stylePreset != null && canvasPreset != null) {
                mutableState.value = DetailUiState.Loading
                imageRepository.generateImageToImage(
                    originalImageByteArray,
                    imageStrength,
                    prompt,
                    styleId,
                    canvasPreset.width,
                    canvasPreset.height
                )
                    .onSuccess { projectInfo ->
                        if (projectInfo.originalImage != null) {
                            val originalImageBitmap =
                                imageBitmapFromBytes(projectInfo.originalImage)
                            val generatedImageBitmap =
                                imageBitmapFromBytes(projectInfo.generatedImage)
                            mutableState.value = DetailUiState.Success(
                                projectType = projectInfo.projectType,
                                originalImageBitmap = originalImageBitmap,
                                generatedImageBitmap = generatedImageBitmap,
                                prompt = prompt,
                                styleName = stylePreset.displayName,
                                canvasName = canvasPreset.id,
                                aspectRatio = canvasPreset.aspectRatio,
                            )
                        }
                    }
                    .onFailure {
                        mutableState.value = DetailUiState.Error(it.message ?: "Unknown error")
                    }
            } else {
                mutableState.value = DetailUiState.Error("Unsupported Style or Canvas")
            }
        }
}