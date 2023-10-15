package com.vproject.texttoimage.core.network.retrofit

import com.vproject.texttoimage.core.network.BuildConfig
import com.vproject.texttoimage.core.network.TextToImageNetworkDataSource
import com.vproject.texttoimage.core.network.model.QueuedImageRequestBody
import com.vproject.texttoimage.core.network.model.QueuedImageResponseBody
import com.vproject.texttoimage.core.network.model.TextToImageResponseBody
import com.vproject.texttoimage.core.network.model.TextToImageRequestBody
import okhttp3.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

/**
 * [Retrofit] backed [TextToImageNetworkDataSource]
 */
@Singleton
internal class RetrofitTextToImageNetwork @Inject constructor(okhttpCallFactory: Call.Factory) :
    TextToImageNetworkDataSource {
    companion object {
        private const val STABLE_DIFFUSION_BASE_URL = "https://stablediffusionapi.com"
        private const val STABLE_DIFFUSION_API_KEY = BuildConfig.STABLE_DIFFUSION_API_KEY
    }

    private val networkApi = Retrofit.Builder()
        .baseUrl(STABLE_DIFFUSION_BASE_URL)
        .callFactory(okhttpCallFactory)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RetrofitTextToImageApi::class.java)

    /**
     * Method to request generates and returns an image from a prompt API.
     *
     * @param prompt Text prompt with description of the things you want in the image to be generated.
     * @param negativePrompt Items you don't want in the image.
     *
     * @return text to image response body.
     */
    override suspend fun postTextToImage(prompt: String, negativePrompt: String): TextToImageResponseBody =
        networkApi.postTextToImage(
            TextToImageRequestBody(
                key = STABLE_DIFFUSION_API_KEY,
                prompt = prompt,
                negative_prompt = negativePrompt
            )
        )

    /**
     * Method to request queued images from stable diffusion API.
     * Usually more complex image generation requests take more time for processing.
     * Such requests are being queued for processing and the output images are retrievable after some time.
     *
     * @param id The ID returned together with the image URL in the response upon its generation.
     *
     * @return queued image response body.
     */
    override suspend fun fetchQueuedImage(id: Long): QueuedImageResponseBody =
        networkApi.fetchQueuedImage(
            id, QueuedImageRequestBody(key = STABLE_DIFFUSION_API_KEY)
        )
}