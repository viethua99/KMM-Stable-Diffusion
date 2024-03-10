package com.vproject.stablediffusion.util

import androidx.compose.runtime.Composable

@Composable
actual fun rememberGalleryManager(onResult: (SharedImage?) -> Unit): GalleryManager {
    throw UnsupportedOperationException("Not supported on desktop")
}

actual class GalleryManager actual constructor(private val onLaunch: () -> Unit) {
    actual fun launch() {
        onLaunch()
    }
}