package com.vproject.texttoimage.core.domain

import com.vproject.texttoimage.core.data.repository.image.ImageRepository
import com.vproject.texttoimage.core.data.repository.style.StyleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

/**
 * A use case to generate an image based on the provided prompt.
 */
class GenerateImageUseCase @Inject constructor(
    private val styleRepository: StyleRepository,
    private val imageRepository: ImageRepository,
) {
    suspend operator fun invoke(prompt: String, styleId: String): Flow<String> {
       return styleRepository.getStyle(styleId).flatMapLatest { style ->
           imageRepository.generateImage("$prompt, ${style.fullDescription}")
       }
    }
}