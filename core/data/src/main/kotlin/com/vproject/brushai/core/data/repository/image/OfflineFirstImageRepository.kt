package com.vproject.brushai.core.data.repository.image

import com.vproject.brushai.core.common.network.BrushAiDispatchers.IO
import com.vproject.brushai.core.common.network.Dispatcher
import com.vproject.brushai.core.network.BrushAiNetworkDataSource
import com.vproject.brushai.core.network.model.TextToImageRequestBody
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class OfflineFirstImageRepository @Inject constructor(
    private val network: BrushAiNetworkDataSource,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
) : ImageRepository {

    override suspend fun generateImage(prompt: String): Flow<String> = withContext(ioDispatcher) {
        val output = network.postTextToImage(TextToImageRequestBody(prompt = prompt, negative_prompt = "")).output.first()
        flow { emit(output) }
    }
}