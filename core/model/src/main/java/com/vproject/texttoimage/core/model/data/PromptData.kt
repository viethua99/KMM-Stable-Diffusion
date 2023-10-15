package com.vproject.texttoimage.core.model.data

/**
 * External data layer representation of a Text To Image Prompt
 */
data class PromptData(
    val id: Long = 0,
    val status: PromptStatus,
    val imageUrl: String? = null,
    val content: String = ""
)