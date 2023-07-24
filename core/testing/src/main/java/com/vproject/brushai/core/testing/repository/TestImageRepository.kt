package com.vproject.brushai.core.testing.repository

import com.vproject.brushai.core.data.repository.image.ImageRepository

class TestImageRepository : ImageRepository {
    override suspend fun generateImage(prompt: String): String {
        return if (prompt.isEmpty()) {
            ""
        } else {
            "https://cdn.stablediffusionapi.com/generations/a8fcf169-b467-41e4-924b-6c168ed73a71-0.png"
        }
    }
}