package com.vproject.brushai.core.network

import com.vproject.brushai.core.network.model.TextToImageResponse
import com.vproject.brushai.core.network.model.TextToImageRequestBody

interface BrushAiNetworkDataSource {
    suspend fun postTextToImage(textToImageRequestBody: TextToImageRequestBody): TextToImageResponse
}