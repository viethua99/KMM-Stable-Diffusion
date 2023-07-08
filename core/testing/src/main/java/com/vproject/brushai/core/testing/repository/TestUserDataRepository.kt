package com.vproject.brushai.core.testing.repository

import com.vproject.brushai.core.data.repository.userdata.UserDataRepository
import com.vproject.brushai.core.model.data.UserData
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filterNotNull

class TestUserDataRepository : UserDataRepository {
    /**
     * The backing hot flow for the list of followed topic ids for testing.
     */
    private val _userData =
        MutableSharedFlow<UserData>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    private val currentUserData get() = _userData.replayCache.firstOrNull() ?: emptyUserData

    override val userData: Flow<UserData> = _userData.filterNotNull()

    override suspend fun toggleFavoriteStyleId(styleId: String, isFavorite: Boolean) {
        currentUserData.let { current ->
            val favoriteStyleIds = if (isFavorite) {
                current.favoriteStyleIds + styleId
            } else {
                current.favoriteStyleIds - styleId
            }
            _userData.tryEmit(current.copy(favoriteStyleIds = favoriteStyleIds))
        }
    }

    /**
     * A test-only API to allow controlling the favorite style ids
     */
    fun setFavoriteStyleIds(favoriteStyleIds: Set<String>) {
        _userData.tryEmit(currentUserData.copy(favoriteStyleIds = favoriteStyleIds))
    }
}

val emptyUserData = UserData(favoriteStyleIds = emptySet())