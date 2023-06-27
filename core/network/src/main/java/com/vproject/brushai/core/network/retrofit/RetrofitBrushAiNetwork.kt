package com.vproject.brushai.core.network.retrofit

import com.vproject.brushai.core.network.BrushAiNetworkDataSource
import com.vproject.brushai.core.network.model.TextToImageResponse
import com.vproject.brushai.core.network.model.TextToImageRequestBody
import okhttp3.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

/**
 * [Retrofit] backed [BrushAiNetworkDataSource]
 */
@Singleton
class RetrofitBrushAiNetwork @Inject constructor(okhttpCallFactory: Call.Factory) : BrushAiNetworkDataSource {
    companion object {
        private const val BRUSH_AI_BASE_URL = "https://stablediffusionapi.com"
    }

    private val networkApi = Retrofit.Builder()
        .baseUrl(BRUSH_AI_BASE_URL)
        .callFactory(okhttpCallFactory)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RetrofitBrushAiApi::class.java)

    override suspend fun postTextToImage(textToImageRequestBody: TextToImageRequestBody): TextToImageResponse =
        networkApi.postTextToImage(textToImageRequestBody)
}