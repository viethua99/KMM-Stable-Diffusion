package com.vproject.stablediffusion.repository.image

import com.vproject.stablediffusion.model.GeneratedImageInfo

interface ImageRepository {
    /**
     * Method to request generating images from prompts information.
     *
     * @param prompt Prompt content that need to generate.
     * @param styleId The selected style preset.
     * @param canvasId The selected canvas preset.
     *
     * @return generated image information.
     */
    suspend fun generateImage(prompt: String, styleId: String, canvasId: String): Result<GeneratedImageInfo>
}