package com.vproject.texttoimage.core.network

import com.vproject.texttoimage.core.network.model.TextToImageResponse
import com.vproject.texttoimage.core.network.model.TextToImageRequestBody

interface TextToImageNetworkDataSource {
    suspend fun postTextToImage(textToImageRequestBody: TextToImageRequestBody): TextToImageResponse
}