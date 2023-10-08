package com.vproject.texttoimage.core.domain

import com.vproject.texttoimage.core.model.data.FavorableStyle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

/**
 * A use case which returns the style that you need.
 */
class GetFavorableStyleUseCase @Inject constructor(
    private val getFavorableStyleListUseCase: GetFavorableStyleListUseCase,
) {
    operator fun invoke(styleId: String): Flow<FavorableStyle> {
        return getFavorableStyleListUseCase().mapLatest { favorableStyleList ->
            favorableStyleList.first { it.style.id == styleId}
        }
    }
}