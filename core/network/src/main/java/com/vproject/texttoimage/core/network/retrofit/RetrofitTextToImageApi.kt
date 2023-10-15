package com.vproject.texttoimage.core.network.retrofit

import com.vproject.texttoimage.core.network.model.QueuedImageRequestBody
import com.vproject.texttoimage.core.network.model.QueuedImageResponseBody
import com.vproject.texttoimage.core.network.model.TextToImageResponseBody
import com.vproject.texttoimage.core.network.model.TextToImageRequestBody
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Retrofit API declaration for Text To Image Network API
 */
internal interface RetrofitTextToImageApi {
    @POST(value = "api/v3/text2img")
    suspend fun postTextToImage(
        @Body textToImageRequestBody: TextToImageRequestBody,
    ): TextToImageResponseBody

    @POST(value = "api/v3/fetch/{id}")
    suspend fun fetchQueuedImage(
        @Path("id") id: Long,
        @Body textToImageRequestBody: QueuedImageRequestBody,
    ): QueuedImageResponseBody
}