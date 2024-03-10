package com.vproject.stablediffusion.util

import androidx.compose.ui.graphics.ImageBitmap

actual fun imageBitmapFromBytes(encodedImageData: ByteArray): ImageBitmap {
    throw UnsupportedOperationException("Not supported on desktop")
}