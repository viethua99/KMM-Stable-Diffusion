package com.vproject.texttoimage.core.testing.repository

import com.vproject.texttoimage.core.data.repository.image.ImageRepository
import com.vproject.texttoimage.core.model.data.PromptData
import com.vproject.texttoimage.core.model.data.PromptStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TestImageRepository : ImageRepository {
    override suspend fun generateImage(prompt: String, negativePrompt: String): Flow<PromptData> {
        return flow {
            if (prompt.isEmpty()) {
                emit(PromptData(0, PromptStatus.Processing, "", ""))
            } else {
                emit(PromptData(0, PromptStatus.Success, "", ""))
            }
        }
    }

    override suspend fun fetchQueuedImage(id: Long): Flow<PromptData> {
        TODO("Not yet implemented")
    }

    override fun getGeneratedPromptList(): Flow<List<PromptData>> {
        TODO("Not yet implemented")
    }

    override fun getTopTrendingPromptList(): Flow<List<PromptData>> {
        TODO("Not yet implemented")
    }
}