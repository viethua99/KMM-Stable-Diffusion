package com.vproject.stablediffusion.repository

import com.vproject.stablediffusion.network.StableDiffusionApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DefaultImageRepository(private val stableDiffusionApi: StableDiffusionApi): ImageRepository {
    override fun generateImage(prompt: String, negativePrompt: String): Flow<String> {
       return flow {
           val data = stableDiffusionApi.postTextToImage(prompt, negativePrompt)
           emit(data)
       }
    }
}