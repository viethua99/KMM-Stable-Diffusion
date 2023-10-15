package com.vproject.texttoimage.core.domain

import com.vproject.texttoimage.core.data.repository.style.StyleRepository
import com.vproject.texttoimage.core.data.repository.userdata.UserDataRepository
import com.vproject.texttoimage.core.model.data.FavorableStyle
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
            styles.map { style ->
                FavorableStyle(style = style, isFavorite = style.id in userData.favoriteStyleIds)
            }.sortedBy { !it.isFavorite }
        }
    }
}