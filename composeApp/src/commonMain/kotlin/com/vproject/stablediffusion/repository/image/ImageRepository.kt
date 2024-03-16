package com.vproject.stablediffusion.repository.image

import com.vproject.stablediffusion.model.ProjectInfo
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    /**
     * Method to request generating images from prompts information.
     *
     * @param prompt Prompt content that need to generate.
     * @param styleId The selected style preset.
     * @param width The width of requesting image.
     * @param height The height of requesting image.
     *
     * @return generated image information.
     */
    suspend fun generateTextToImage(
        prompt: String,
        styleId: String,
        width: Long,
        height: Long
    ): Result<ProjectInfo>

    /**
     * Method to request generating images from original image information.
     *
     * @param originalImageByteArray original image byte array that need to generate.
     * @param imageStrength How much influence the image has on the diffusion process.
     * @param prompt Prompt content that need to generate.
     * @param styleId The selected style preset.
     * @param width The width of requesting image.
     * @param height The height of requesting image.
     *
     * @return generated image information.
     */
    suspend fun generateImageToImage(
        originalImageByteArray: ByteArray,
        imageStrength: Double,
        prompt: String,
        styleId: String,
        width: Long,
        height: Long
    ): Result<ProjectInfo>

    /**
     * Method to request getting detail information of an image project.
     *
     * @param id identifier of the project.
     *
     * @return detail information of requested project
     */
    suspend fun getProjectDetail(id: Long): ProjectInfo?

    /**
     * Method to request generated images.
     *
     * @return generated image list information.
     */
    suspend fun getProjectInfoList(): Flow<List<ProjectInfo>>
}