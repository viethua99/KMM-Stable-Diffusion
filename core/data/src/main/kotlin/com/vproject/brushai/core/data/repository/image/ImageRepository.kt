package com.vproject.brushai.core.data.repository.image

interface ImageRepository {
    suspend fun generateImage(prompt: String) : String
}