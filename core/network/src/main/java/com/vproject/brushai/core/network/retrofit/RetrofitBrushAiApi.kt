package com.vproject.brushai.core.network.retrofit

import com.vproject.brushai.core.network.model.TextToImageResponse
import com.vproject.brushai.core.network.model.TextToImageRequestBody
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Retrofit API declaration for Brush AI Network API
 */
interface RetrofitBrushAiApi {
    @POST(value = "api/v3/text2img")
    suspend fun postTextToImage(
        @Body textToImageRequestBody: TextToImageRequestBody,
    ): TextToImageResponse
}