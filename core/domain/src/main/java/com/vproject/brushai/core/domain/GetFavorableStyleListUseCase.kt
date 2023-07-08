package com.vproject.brushai.core.domain

import com.vproject.brushai.core.data.repository.style.StyleRepository
import com.vproject.brushai.core.data.repository.userdata.UserDataRepository
import com.vproject.brushai.core.model.data.FavorableStyle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

/**
 * A use case which returns the style list with their favorable state.
 */
class GetFavorableStyleListUseCase @Inject constructor(
    private val userDataRepository: UserDataRepository,
    private val styleRepository: StyleRepository,
) {
    operator fun invoke(): Flow<List<FavorableStyle>> {
        return combine(
            userDataRepository.userData,
            styleRepository.getStyles()
        ) { userData, styles ->
            val favoriteStyles = styles.map { style ->
                FavorableStyle(style = style, isFavorite = style.id in userData.favoriteStyleIds)
            }
            favoriteStyles.sortedBy { !it.isFavorite }
        }
    }
}