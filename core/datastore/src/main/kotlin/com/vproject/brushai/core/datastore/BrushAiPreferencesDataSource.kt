package com.vproject.brushai.core.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import com.vproject.brushai.core.model.data.UserData
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class BrushAiPreferencesDataSource @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>,
) {
    val userData = userPreferences.data
        .map {
            UserData(favoriteStyleIds = it.favoriteStyleIdsMap.keys)
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
            Log.e("BrushAiPreferences", "Failed to update user preferences", ioException)
        }
    }
}