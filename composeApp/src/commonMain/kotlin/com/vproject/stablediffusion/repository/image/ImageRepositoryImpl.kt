package com.vproject.stablediffusion.repository.image

import com.vproject.stablediffusion.database.dao.ImageDao
import com.vproject.stablediffusion.model.CanvasPreset
import com.vproject.stablediffusion.model.ProjectInfo
import com.vproject.stablediffusion.network.StableDiffusionApi
import com.vproject.stablediffusion.stableDiffusionDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ImageRepositoryImpl(
    private val stableDiffusionApi: StableDiffusionApi,
    private val imageDao: ImageDao
) : ImageRepository {
    /**
     * Method to request generating images from prompts information.
     *
     * @param prompt Prompt content that need to generate.
     * @param styleId The selected style preset.
     * @param canvasId The selected canvas preset.
     *
     * @return generated image information.
     */
    override suspend fun generateTextToImage(
        prompt: String,
        styleId: String,
        canvasId: String
    ): Result<ProjectInfo> = withContext(
        stableDiffusionDispatchers.io
    ) {
        return@withContext try {
            val width = CanvasPreset.entries.find { it.id == canvasId }?.width ?: 1024
            val height = CanvasPreset.entries.find { it.id == canvasId }?.height ?: 1024

            val response = stableDiffusionApi.postTextToImage(prompt, styleId, width, height)
            val generatedImageInfo = response.artifacts[0].toGeneratedImageInfo()
            val entity = generatedImageInfo.toImageEntity()
                .copy(
                    projectType = "tti",
                    prompt = prompt,
                    styleId = styleId,
                    canvasId = canvasId
                )
            imageDao.insert(entity)
            Result.success(entity.toProjectInfo())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Method to request generating images from original image information.
     *
     * @param originalImageByteArray original image byte array that need to generate.
     * @param prompt Prompt content that need to generate.
     * @param styleId The selected style preset.
     * @param canvasId The selected canvas preset.
     *
     * @return generated image information.
     */
    override suspend fun generateImageToImage(
        originalImageByteArray: ByteArray,
        prompt: String,
        styleId: String,
        canvasId: String
    ): Result<ProjectInfo> = withContext(
        stableDiffusionDispatchers.io
    ) {
        return@withContext try {
            val response =
                stableDiffusionApi.postImageToImage(originalImageByteArray, prompt, styleId)
            val generatedImageInfo = response.artifacts[0].toGeneratedImageInfo()
//            val entity = generatedImageInfo.toImageEntity()
//                .copy(
//                    projectType = "iti",
//                    prompt = prompt,
//                    styleId = styleId,
//                    canvasId = canvasId,
//                    originalImage = originalImageByteArray
//                )
//            imageDao.insert(entity)
            Result.success(
                generatedImageInfo.toProjectInfo().copy(
                    projectType = "iti",
                    prompt = prompt,
                    styleId = styleId,
                    canvasId = canvasId,
                    originalImage = originalImageByteArray
                )
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Method to request getting detail information of an image project.
     *
     * @param id identifier of the project.
     *
     * @return detail information of requested project
     */
    override suspend fun getProjectDetail(id: Long): ProjectInfo? =
        withContext(stableDiffusionDispatchers.io) {
            val imageEntity = imageDao.getImageDetailById(id = id)
            return@withContext imageEntity?.toProjectInfo()
        }

    /**
     * Method to request generated images.
     *
     * @return generated image list information.
     */
    override suspend fun getProjectInfoList(): Flow<List<ProjectInfo>> {
        return imageDao.getAllGeneratedImages().map { imageEntities ->
            imageEntities.map { it.toProjectInfo() }
        }
    }
}