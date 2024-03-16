package com.vproject.stablediffusion.database.dao

import app.cash.sqldelight.coroutines.asFlow
import com.vproject.stablediffusion.TextToImageEntity
import com.vproject.stablediffusion.database.StableDiffusionDatabase
import com.vproject.stablediffusion.stableDiffusionDispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class TextToImageDao(private val stableDiffusionDatabase: StableDiffusionDatabase) {
    private val query get() = stableDiffusionDatabase.textToImageEntityQueries

    suspend fun insert(textToImageEntity: TextToImageEntity) = withContext(stableDiffusionDispatchers.io) {
        query.insert(
            null,
            textToImageEntity.generatedImage,
            textToImageEntity.prompt,
            textToImageEntity.styleId,
            textToImageEntity.width,
            textToImageEntity.height,
            textToImageEntity.finishReason,
            textToImageEntity.seed,
            textToImageEntity.timestamp
        )
    }

    suspend fun getImageDetailById(id: Long) = withContext(stableDiffusionDispatchers.io) {
        query.selectImageById(id = id).executeAsOneOrNull()
    }

    suspend fun getAllGeneratedImages() = withContext(stableDiffusionDispatchers.io) {
        query.selectAllGeneratedImages().asFlow().map { it.executeAsList() }
    }
}