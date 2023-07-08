package com.vproject.brushai.core.data.repository.userdata

import com.vproject.brushai.core.model.data.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
    /**
     * Stream of [UserData]
     */
    val userData: Flow<UserData>

    /**
     * Toggles the user's newly favorite style
     */
    suspend fun toggleFavoriteStyleId(styleId: String, isFavorite: Boolean)
}