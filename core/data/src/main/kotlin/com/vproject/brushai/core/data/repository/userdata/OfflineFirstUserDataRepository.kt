package com.vproject.brushai.core.data.repository.userdata

import com.vproject.brushai.core.datastore.BrushAiPreferencesDataSource
import com.vproject.brushai.core.model.data.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineFirstUserDataRepository @Inject constructor(
    private val brushAiPreferencesDataSource: BrushAiPreferencesDataSource
    ) : UserDataRepository {
    override val userData: Flow<UserData> = brushAiPreferencesDataSource.userData

    override suspend fun toggleFavoriteStyleId(styleId: String, isFavorite: Boolean) {
        brushAiPreferencesDataSource.toggleFavoriteStyleId(styleId, isFavorite)
    }
}