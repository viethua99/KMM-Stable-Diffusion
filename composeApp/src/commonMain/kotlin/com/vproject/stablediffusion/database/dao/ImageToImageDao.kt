package com.vproject.stablediffusion.database.dao

import app.cash.sqldelight.coroutines.asFlow
import com.vproject.stablediffusion.ImageToImageEntity
import com.vproject.stablediffusion.database.StableDiffusionDatabase
import com.vproject.stablediffusion.stableDiffusionDispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ImageToImageDao(private val stableDiffusionDatabase: StableDiffusionDatabase) {
    private val query get() = stableDiffusionDatabase.imageToImageEntityQueries

    suspend fun insert(imageToImageEntity: ImageToImageEntity) = withContext(stableDiffusionDispatchers.io) {
        query.insert(
            null,
            imageToImageEntity.originalImage,
            imageToImageEntity.generatedImage,
            imageToImageEntity.imageStrength,
            imageToImageEntity.prompt,
            imageToImageEntity.styleId,
            imageToImageEntity.width,
            imageToImageEntity.height,
            imageToImageEntity.finishReason,
            imageToImageEntity.seed,
            imageToImageEntity.timestamp
        )
    }

    suspend fun getImageDetailById(id: Long) = withContext(stableDiffusionDispatchers.io) {
        query.selectImageById(id = id).executeAsOneOrNull()
    }

    suspend fun getAllGeneratedImages() = withContext(stableDiffusionDispatchers.io) {
        query.selectAllGeneratedImages().asFlow().map { it.executeAsList() }
    }
}