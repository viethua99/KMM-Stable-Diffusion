package com.vproject.stablediffusion.util

import androidx.compose.ui.graphics.ImageBitmap

actual fun imageBitmapFromBytes(encodedImageData: ByteArray): ImageBitmap {
    throw UnsupportedOperationException("Not supported on desktop")
}

actual class TestUtil {
    actual fun isInternetAvailable(): Boolean {
        return false
    }
}