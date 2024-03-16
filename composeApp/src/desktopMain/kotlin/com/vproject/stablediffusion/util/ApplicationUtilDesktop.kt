package com.vproject.stablediffusion.util

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.skia.Image
import java.util.UUID

actual fun imageBitmapFromBytes(encodedImageData: ByteArray): ImageBitmap {
    throw UnsupportedOperationException("Not supported on desktop")
}

actual fun createUUID(): String = UUID.randomUUID().toString()

actual fun getCurrentTimeMillis() = System.currentTimeMillis()

actual fun ByteArray.toImageBitmap(): ImageBitmap =
    Image.makeFromEncoded(this).toComposeImageBitmap()

class DesktopStorableImage(
    val imageBitmap: ImageBitmap
)

actual typealias PlatformStorableImage = DesktopStorableImage