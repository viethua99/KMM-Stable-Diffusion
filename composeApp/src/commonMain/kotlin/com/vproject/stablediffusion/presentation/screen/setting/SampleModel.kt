package com.vproject.stablediffusion.presentation.screen.sample

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.vproject.stablediffusion.model.CanvasPreset
import com.vproject.stablediffusion.model.StableDiffusionMode
import com.vproject.stablediffusion.model.StylePreset
import com.vproject.stablediffusion.model.imageToImageSampleList
import com.vproject.stablediffusion.model.textToImageSampleList
import com.vproject.stablediffusion.repository.image.ImageRepository
import com.vproject.stablediffusion.util.imageBitmapFromBytes
import kotlinx.coroutines.launch

class SampleModel(private val imageRepository: ImageRepository) :
    StateScreenModel<SampleUiState>(SampleUiState.Initial) {
    fun getSampleImage(id: Long, stableDiffusionMode: StableDiffusionMode) =
        screenModelScope.launch {
           if (stableDiffusionMode == StableDiffusionMode.TEXT_TO_IMAGE) {
               val sample = textToImageSampleList.firstOrNull { it.id == id }
               sample?.let { nonNullSample ->
                   mutableState.value = SampleUiState.TextToImageLoaded(
                       nonNullSample.imageResource,
                       nonNullSample.prompt,
                       nonNullSample.stylePreset.displayName,
                       nonNullSample.canvasPreset.id,
                       nonNullSample.canvasPreset.aspectRatio
                   )
               }
           } else {
               val sample = imageToImageSampleList.firstOrNull { it.id == id }
               sample?.let { nonNullSample ->
                   mutableState.value = SampleUiState.ImageToImageLoaded(
                       nonNullSample.originalImageResource,
                       nonNullSample.generatedImageResource,
                       nonNullSample.prompt,
                       nonNullSample.stylePreset.displayName,
                       nonNullSample.canvasPreset.id,
                       nonNullSample.canvasPreset.aspectRatio
                   )
               }
           }
        }
}