package com.vproject.brushai.core.data.repository.image

import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    suspend fun generateImage(prompt: String) : Flow<String>
}