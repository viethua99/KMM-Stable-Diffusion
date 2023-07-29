package com.vproject.brushai.core.data.repository.userdata

import com.vproject.brushai.core.model.data.DarkThemeConfig
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

    /**
     * Sets the desired prompt CFG scale value.
     */
    suspend fun setPromptCfgScaleValue(promptCfgScaleValue: Float)

    /**
     * Sets the desired prompt step value.
     */
    suspend fun setPromptStepValue(promptStepValue: Float)

    /**
     * Sets the desired dark theme config.
     */
    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig)

}