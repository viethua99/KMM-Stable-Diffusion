package com.vproject.stablediffusion.repository

import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    fun generateImage(prompt: String, negativePrompt: String) : Flow<String>
}