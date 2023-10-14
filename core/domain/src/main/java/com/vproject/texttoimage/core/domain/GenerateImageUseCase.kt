package com.vproject.texttoimage.core.domain

import com.vproject.texttoimage.core.data.repository.image.ImageRepository
import com.vproject.texttoimage.core.data.repository.style.StyleRepository
import com.vproject.texttoimage.core.model.data.PromptData
import com.vproject.texttoimage.core.model.data.PromptStatus
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * A use case to generate an image based on the provided prompt.
 */
class GenerateImageUseCase @Inject constructor(
    private val styleRepository: StyleRepository,
    private val imageRepository: ImageRepository,
) {
     operator fun invoke(prompt: String, styleId: String): Flow<PromptData> {
       return styleRepository.getStyle(styleId).flatMapLatest { style ->
           imageRepository.generateImage("$prompt, ${style.prompt}", style.negativePrompt).flatMapLatest { promptData ->
               when (promptData.status) {
                   PromptStatus.Processing -> {
                       delay(40000)
                       imageRepository.fetchQueuedImage(promptData.id)
                   }

                   else -> {
                       flow { emit(promptData) }
                   }
               }
           }
       }
    }
}