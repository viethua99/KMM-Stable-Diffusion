package com.vproject.brushai.core.domain

import com.vproject.brushai.core.data.repository.image.ImageRepository
import javax.inject.Inject

/**
 * A use case to generate an image based on the provided prompt.
 */
class GenerateImageUseCase @Inject constructor(
    private val imageRepository: ImageRepository,
) {
    suspend operator fun invoke(prompt: String): String = imageRepository.generateImage(prompt)
}