package com.vproject.brushai.core.data.repository.image

import com.vproject.brushai.core.common.network.BrushAiDispatchers.IO
import com.vproject.brushai.core.common.network.Dispatcher
import com.vproject.brushai.core.network.BrushAiNetworkDataSource
import com.vproject.brushai.core.network.model.TextToImageRequestBody
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OfflineFirstImageRepository @Inject constructor(
    private val network: BrushAiNetworkDataSource,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
    ) : ImageRepository {

    override suspend fun generateImage(prompt: String): String = withContext(ioDispatcher) {
        network.postTextToImage(TextToImageRequestBody(prompt = prompt)).output.first()
    }
}