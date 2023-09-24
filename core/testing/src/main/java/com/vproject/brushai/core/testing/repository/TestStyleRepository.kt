package com.vproject.brushai.core.testing.repository

import com.vproject.brushai.core.data.repository.style.StyleRepository
import com.vproject.brushai.core.model.data.Style
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map

class TestStyleRepository: StyleRepository {
    /**
     * The backing hot flow for the list of styles ids for testing.
     */
    private val stylesFlow: MutableSharedFlow<List<Style>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun getStyles(): Flow<List<Style>> = stylesFlow
    override fun getStyle(id: String): Flow<Style> = stylesFlow.map { styles -> styles.find { it.id == id }!! }

    /**
     * A test-only API to allow controlling the list of styles from tests.
     */
    fun sendStyles(styles: List<Style>) {
        stylesFlow.tryEmit(styles)
    }
}