package com.vproject.texttoimage.core.network.retrofit

import com.vproject.texttoimage.core.network.model.TextToImageResponse
import com.vproject.texttoimage.core.network.model.TextToImageRequestBody
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Retrofit API declaration for Text To Image Network API
 */
interface RetrofitTextToImageApi {
    @POST(value = "api/v3/text2img")
    suspend fun postTextToImage(
        @Body textToImageRequestBody: TextToImageRequestBody,
    ): TextToImageResponse
}