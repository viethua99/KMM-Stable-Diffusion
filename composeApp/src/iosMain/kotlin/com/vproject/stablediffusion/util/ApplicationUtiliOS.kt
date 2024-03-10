package com.vproject.stablediffusion.util

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.skia.Image

actual fun imageBitmapFromBytes(encodedImageData: ByteArray): ImageBitmap {
    return Image.makeFromEncoded(encodedImageData).toComposeImageBitmap()
}