package com.vproject.brushai.core.data.repository.userdata

import com.vproject.brushai.core.model.data.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OfflineFirstUserDataRepository @Inject constructor() : UserDataRepository {
    override val userData: Flow<UserData> = flow { emit(UserData(setOf()))  }

    override suspend fun toggleFavoriteStyleId(styleId: String, isFavorite: Boolean) {

    }
}