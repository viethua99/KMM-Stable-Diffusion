package com.vproject.texttoimage.core.data.repository.image

import com.vproject.texttoimage.core.model.data.PromptData
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    suspend fun generateImage(prompt: String, negativePrompt: String) : Flow<PromptData>
    suspend fun fetchQueuedImage(id: Long) : Flow<PromptData>
    fun getGeneratedPromptList() : Flow<List<PromptData>>
    fun getTopTrendingPromptList() : Flow<List<PromptData>>
}