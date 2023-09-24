package com.vproject.texttoimage.core.data.repository.userdata

import com.vproject.texttoimage.core.datastore.TextToImagePreferencesDataSource
import com.vproject.texttoimage.core.model.data.DarkThemeConfig
import com.vproject.texttoimage.core.model.data.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class OfflineFirstUserDataRepository @Inject constructor(
    private val textToImagePreferencesDataSource: TextToImagePreferencesDataSource
    ) : UserDataRepository {
    override val userData: Flow<UserData> = textToImagePreferencesDataSource.userData

    override suspend fun toggleFavoriteStyleId(styleId: String, isFavorite: Boolean) {
        textToImagePreferencesDataSource.toggleFavoriteStyleId(styleId, isFavorite)
    }

    override suspend fun setPromptCfgScaleValue(promptCfgScaleValue: Float) {
        textToImagePreferencesDataSource.setPromptCfgScaleValue(promptCfgScaleValue)

    }

    override suspend fun setPromptStepValue(promptStepValue: Float) {
        textToImagePreferencesDataSource.setPromptStepValue(promptStepValue)
    }

    override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        textToImagePreferencesDataSource.setDarkThemeConfig(darkThemeConfig)
    }
}