package com.vproject.stablediffusion.repository.image

import com.vproject.stablediffusion.database.dao.ImageToImageDao
import com.vproject.stablediffusion.database.dao.TextToImageDao
import com.vproject.stablediffusion.model.ProjectInfo
import com.vproject.stablediffusion.network.StableDiffusionApi
import com.vproject.stablediffusion.stableDiffusionDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.withContext

class ImageRepositoryImpl(
    private val stableDiffusionApi: StableDiffusionApi,
    private val textToImageDao: TextToImageDao,
    private val imageToImageDao: ImageToImageDao,
) : ImageRepository {
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
    override suspend fun generateTextToImage(
        prompt: String,
        styleId: String,
        width: Long,
        height: Long
    ): Result<ProjectInfo> = withContext(
        stableDiffusionDispatchers.io
    ) {
        return@withContext try {
            val response = stableDiffusionApi.postTextToImage(prompt, styleId, width, height)
            val generatedImageInfo = response.artifacts[0].toGeneratedImageInfo()

            val textToImageEntity =
                generatedImageInfo.toTextToImageEntity(prompt, styleId, width, height)
            textToImageDao.insert(textToImageEntity)
            Result.success(textToImageEntity.toProjectInfo())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

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
    override suspend fun generateImageToImage(
        originalImageByteArray: ByteArray,
        imageStrength: Double,
        prompt: String,
        styleId: String,
        width: Long,
        height: Long
    ): Result<ProjectInfo> = withContext(
        stableDiffusionDispatchers.io
    ) {
        return@withContext try {
            val response =
                stableDiffusionApi.postImageToImage(
                    originalImageByteArray,
                    imageStrength,
                    prompt,
                    styleId
                )
            val generatedImageInfo = response.artifacts[0].toGeneratedImageInfo()

            val imageToImageEntity = generatedImageInfo.toImageToImageEntity(
                originalImageByteArray,
                imageStrength,
                prompt,
                styleId,
                width,
                height
            )

            imageToImageDao.insert(imageToImageEntity)
            Result.success(imageToImageEntity.toProjectInfo())
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
            val imageEntity = textToImageDao.getImageDetailById(id = id)
            return@withContext imageEntity?.toProjectInfo()
        }

    /**
     * Method to request generated images.
     *
     * @return generated image list information.
     */
    override suspend fun getProjectInfoList(): Flow<List<ProjectInfo>> {
        return combine(
            textToImageDao.getAllGeneratedImages(),
            imageToImageDao.getAllGeneratedImages()
        ) { textToImageEntities, imageToImageEntities ->
            val textToImageProjects =
                textToImageEntities.filter { it.generatedImage != null }.map { it.toProjectInfo() }
            val imageToImageProjects =
                imageToImageEntities.filter { it.generatedImage != null }.map { it.toProjectInfo() }
            return@combine (textToImageProjects + imageToImageProjects).sortedBy { it.timestamp }
        }
    }
}