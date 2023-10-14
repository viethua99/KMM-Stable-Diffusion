package com.vproject.texttoimage.core.model.data

/**
 * External data layer representation of a Text To Image Style
 */
data class Style(
    val id: String,
    val name: String,
    val imageUrl: String,
    val prompt: String,
    val negativePrompt: String
)