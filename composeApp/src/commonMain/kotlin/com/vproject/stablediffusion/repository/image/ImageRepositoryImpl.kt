package com.vproject.stablediffusion.repository.image

import com.vproject.stablediffusion.model.GeneratedImageInfo
import com.vproject.stablediffusion.network.StableDiffusionApi
import com.vproject.stablediffusion.stableDiffusionDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class ImageRepositoryImpl(private val stableDiffusionApi: StableDiffusionApi): ImageRepository {
    /**
     * Method to request generating images from prompts information.
     *
     * @param prompt Prompt content that need to generate.
     * @param styleId The selected style preset.
     * @param canvasId The selected canvas preset.
     *
     * @return generated image information.
     */
    override suspend fun generateImage(prompt: String, styleId: String, canvasId: String): Result<GeneratedImageInfo> = withContext(
        stableDiffusionDispatchers.io) {
        return@withContext try {
            val response = stableDiffusionApi.postTextToImage(prompt, styleId)
            val generatedImageInfo = response.artifacts[0].toGeneratedImageInfo()
            Result.success(generatedImageInfo)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}