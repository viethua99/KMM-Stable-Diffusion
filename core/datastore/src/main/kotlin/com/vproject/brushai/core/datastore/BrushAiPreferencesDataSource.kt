package com.vproject.brushai.core.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import com.vproject.brushai.core.model.data.DarkThemeConfig
import com.vproject.brushai.core.model.data.UserData
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class BrushAiPreferencesDataSource @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>,
) {
    val userData = userPreferences.data
        .map {
            UserData(
                favoriteStyleIds = it.favoriteStyleIdsMap.keys,
                promptCfgScaleValue = it.promptCfgScaleValue,
                promptStepValue = it.promptStepValue,
                darkThemeConfig = if (it.darkThemeConfig == DarkThemeConfigProto.DARK_THEME_CONFIG_LIGHT)
                    DarkThemeConfig.LIGHT else DarkThemeConfig.DARK,
            )
        }

    suspend fun toggleFavoriteStyleId(styleId: String, isFavorite: Boolean) {
        try {
            userPreferences.updateData { currentUserPreferences ->
                currentUserPreferences.copy {
                    if (isFavorite) {
                        favoriteStyleIds.put(styleId, true)
                    } else {
                        favoriteStyleIds.remove(styleId)
                    }
                }
            }
        } catch (ioException: IOException) {
            Log.e(
                "BrushAiPreferences",
                "Failed to update user preferences toggleFavoriteStyleId",
                ioException
            )
        }
    }

    suspend fun setPromptCfgScaleValue(promptCfgScaleValue: Float) {
        try {
            userPreferences.updateData { currentUserPreferences ->
                currentUserPreferences.copy {
                    this.promptCfgScaleValue = promptCfgScaleValue
                }
            }
        } catch (ioException: IOException) {
            Log.e(
                "BrushAiPreferences",
                "Failed to update user preferences setPromptCfgScaleValue",
                ioException
            )
        }
    }

    suspend fun setPromptStepValue(promptStepValue: Float) {
        try {
            userPreferences.updateData { currentUserPreferences ->
                currentUserPreferences.copy {
                    this.promptStepValue = promptStepValue
                }
            }
        } catch (ioException: IOException) {
            Log.e(
                "BrushAiPreferences",
                "Failed to update user preferences setPromptStepValue",
                ioException
            )
        }
    }

    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        try {
            userPreferences.updateData { currentUserPreferences ->
                currentUserPreferences.copy {
                    this.darkThemeConfig = when (darkThemeConfig) {
                        DarkThemeConfig.LIGHT -> DarkThemeConfigProto.DARK_THEME_CONFIG_LIGHT
                        DarkThemeConfig.DARK -> DarkThemeConfigProto.DARK_THEME_CONFIG_DARK
                    }
                }
            }
        } catch (ioException: IOException) {
            Log.e(
                "BrushAiPreferences",
                "Failed to update user preferences setDarkThemeConfig",
                ioException
            )
        }
    }
}