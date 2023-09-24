package com.vproject.texttoimage.core.network.retrofit

import com.vproject.texttoimage.core.network.TextToImageNetworkDataSource
import com.vproject.texttoimage.core.network.model.TextToImageResponse
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
class RetrofitTextToImageNetwork @Inject constructor(okhttpCallFactory: Call.Factory) : TextToImageNetworkDataSource {
    companion object {
        private const val TEXT_TO_IMAGE_BASE_URL = "https://stablediffusionapi.com"
    }

    private val networkApi = Retrofit.Builder()
        .baseUrl(TEXT_TO_IMAGE_BASE_URL)
        .callFactory(okhttpCallFactory)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RetrofitTextToImageApi::class.java)

    override suspend fun postTextToImage(textToImageRequestBody: TextToImageRequestBody): TextToImageResponse =
        networkApi.postTextToImage(textToImageRequestBody)
}