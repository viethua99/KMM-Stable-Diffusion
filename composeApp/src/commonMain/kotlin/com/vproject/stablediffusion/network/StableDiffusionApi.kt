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
    suspend fun postTextToImage(prompt: String, stylePreset: String, width: Int, height: Int): ImageResponse

    /**
     * Method to request generates and returns an image from an original image API.
     *
     * @param prompt Text prompt with description of the things you want in the image to be generated.
     * @param stylePreset The selected style preset.
     *
     * @return text to image response body.
     */
    suspend fun postImageToImage(originalImage: ByteArray, prompt: String, stylePreset: String): ImageResponse
}