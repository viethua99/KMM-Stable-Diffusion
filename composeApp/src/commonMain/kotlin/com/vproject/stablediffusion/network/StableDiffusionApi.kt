package com.vproject.stablediffusion.network

import com.vproject.stablediffusion.network.response.ImageResponse

/**
 * API interface for Stability AI Network API
 */
interface StableDiffusionApi {
    /**
     * Method to request generates and returns an image from a prompt API.
     *
     * @param prompt Text prompt with description of the things you want in the image to be generated.
     * @param stylePreset The selected style preset.
     * @param width width of the image
     * @param height height of the image
     *
     * @return text to image response body.
     */
    suspend fun postTextToImage(prompt: String, stylePreset: String, width: Long, height: Long): ImageResponse

    /**
     * Method to request generates and returns an image from an original image API.
     *
     * @param originalImageByteArray original image byte array that need to generate.
     * @param prompt Text prompt with description of the things you want in the image to be generated.
     * @param imageStrength How much influence the image has on the diffusion process.
     * @param stylePreset The selected style preset.
     *
     * @return image to image response body.
     */
    suspend fun postImageToImage(originalImageByteArray: ByteArray, imageStrength: Double, prompt: String, stylePreset: String): ImageResponse
}