package com.vproject.texttoimage.core.network.retrofit

import com.vproject.texttoimage.core.network.TextToImageNetworkDataSource
import com.vproject.texttoimage.core.network.model.TextToImageResponseBody
import com.vproject.texttoimage.core.network.model.TextToImageRequestBody
import kotlinx.coroutines.delay
import okhttp3.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

/**
 * [Retrofit] backed [TextToImageNetworkDataSource]
 */
@Singleton
class RetrofitTextToImageNetwork @Inject constructor(okhttpCallFactory: Call.Factory) :
    TextToImageNetworkDataSource {
    companion object {
        private const val TEXT_TO_IMAGE_BASE_URL = "https://stablediffusionapi.com"
    }

    private val networkApi = Retrofit.Builder()
        .baseUrl(TEXT_TO_IMAGE_BASE_URL)
        .callFactory(okhttpCallFactory)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RetrofitTextToImageApi::class.java)

    override suspend fun postTextToImage(textToImageRequestBody: TextToImageRequestBody): TextToImageResponseBody {
//        networkApi.postTextToImage(textToImageRequestBody)
        delay(3000)
        return TextToImageResponseBody(
            status = "",
            generateTime = 0.0,
            output = listOf(
                "https://pub-3626123a908346a7a8be8d9295f44e26.r2.dev/generations/dd398970-d4c5-4077-86a1-5022b96d8d98-0.png"
            )
        )
    }
}