package com.vproject.texttoimage.core.domain

import com.vproject.texttoimage.core.data.repository.userdata.UserDataRepository
import javax.inject.Inject

/**
 * A use case to toggle a favorite status of the style.
 */
class ToggleFavoriteStyleUseCase @Inject constructor(
    private val userDataRepository: UserDataRepository,
) {
    suspend operator fun invoke(styleId: String, isFavorite: Boolean) {
        userDataRepository.toggleFavoriteStyleId(styleId, isFavorite)
    }
}