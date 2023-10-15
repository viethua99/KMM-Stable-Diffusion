package com.vproject.texttoimage.core.domain

import com.vproject.texttoimage.core.data.repository.image.ImageRepository
import com.vproject.texttoimage.core.model.data.PromptData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * A use case which returns the top trending list.
 */
class GetTopTrendingListUseCase @Inject constructor(
    private val imageRepository: ImageRepository
) {
    operator fun invoke(): Flow<List<PromptData>> {
        return imageRepository.getTopTrendingPromptList()
    }
}