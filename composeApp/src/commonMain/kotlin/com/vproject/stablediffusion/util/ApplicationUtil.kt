package com.vproject.stablediffusion.util

import androidx.compose.ui.graphics.ImageBitmap

expect fun imageBitmapFromBytes(encodedImageData: ByteArray): ImageBitmap

expect class TestUtil {
    fun isInternetAvailable(): Boolean
}
