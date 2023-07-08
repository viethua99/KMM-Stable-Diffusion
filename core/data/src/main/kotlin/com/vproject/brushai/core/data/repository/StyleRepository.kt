package com.vproject.brushai.core.data.repository

import com.vproject.brushai.core.model.data.Style
import kotlinx.coroutines.flow.Flow

interface StyleRepository {
    /**
     * Gets the available styles as a stream
     */
    fun getStyles(): Flow<List<Style>>
}