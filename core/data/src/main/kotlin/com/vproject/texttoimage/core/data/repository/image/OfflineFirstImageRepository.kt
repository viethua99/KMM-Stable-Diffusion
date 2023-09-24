package com.vproject.texttoimage.core.data.repository.image

import com.vproject.texttoimage.core.common.network.TextToImageDispatchers.IO
import com.vproject.texttoimage.core.common.network.Dispatcher
import com.vproject.texttoimage.core.network.TextToImageNetworkDataSource
import com.vproject.texttoimage.core.network.model.TextToImageRequestBody
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class OfflineFirstImageRepository @Inject constructor(
    private val network: TextToImageNetworkDataSource,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
) : ImageRepository {

    override suspend fun generateImage(prompt: String): Flow<String> = withContext(ioDispatcher) {
        val output = network.postTextToImage(TextToImageRequestBody(prompt = prompt, negative_prompt = "")).output.first()
        flow { emit(output) }
    }
}