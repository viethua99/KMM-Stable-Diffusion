package com.vproject.stablediffusion.database.dao

import app.cash.sqldelight.coroutines.asFlow
import com.vproject.stablediffusion.ImageEntity
import com.vproject.stablediffusion.database.StableDiffusionDatabase
import com.vproject.stablediffusion.stableDiffusionDispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ImageDao(private val stableDiffusionDatabase: StableDiffusionDatabase) {
    private val query get() = stableDiffusionDatabase.imageEntityQueries

    suspend fun insert(imageEntity: ImageEntity) = withContext(stableDiffusionDispatchers.io) {
        query.insert(
            null,
            imageEntity.projectType,
            imageEntity.originalImage,
            imageEntity.generatedImage,
            imageEntity.prompt,
            imageEntity.styleId,
            imageEntity.canvasId,
            imageEntity.finishReason,
            imageEntity.seed
        )
    }

    suspend fun getImageDetailById(id: Long) = withContext(stableDiffusionDispatchers.io) {
        query.selectImageById(id = id).executeAsOneOrNull()
    }

    suspend fun getAllGeneratedImages() = withContext(stableDiffusionDispatchers.io) {
        query.selectAllGeneratedImages().asFlow().map { it.executeAsList() }
    }
}