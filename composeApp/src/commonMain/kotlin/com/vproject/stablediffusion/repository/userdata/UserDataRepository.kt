package com.vproject.stablediffusion.repository.userdata

import com.vproject.stablediffusion.model.DarkThemeConfig
import com.vproject.stablediffusion.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

    suspend fun getUserData(): Flow<UserData>

    /**
     * Sets the desired dark theme config.
     */
     suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig)
}