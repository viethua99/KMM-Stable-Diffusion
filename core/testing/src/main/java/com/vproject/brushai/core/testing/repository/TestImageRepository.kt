package com.vproject.brushai.core.testing.repository

import com.vproject.brushai.core.data.repository.image.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TestImageRepository : ImageRepository {
    override suspend fun generateImage(prompt: String): Flow<String> {
        return flow {
            if (prompt.isEmpty()) {
                emit("")
            } else {
                emit("https://cdn.stablediffusionapi.com/generations/a8fcf169-b467-41e4-924b-6c168ed73a71-0.png")
            }
        }
    }
}