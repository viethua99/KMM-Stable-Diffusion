package com.vproject.texttoimage.core.model.data

/**
 * A [style] with the additional information for whether or not it is favorite.
 */
data class FavorableStyle(
    val style: Style,
    val isFavorite: Boolean,
)