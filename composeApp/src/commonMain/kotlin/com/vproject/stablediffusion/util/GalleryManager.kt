package com.vproject.stablediffusion.util

import androidx.compose.runtime.Composable

@Composable
expect fun rememberGalleryManager(onResult: (SharedImage?) -> Unit): GalleryManager

expect class GalleryManager(
    onLaunch: () -> Unit
) {
    fun launch()
}