package com.vproject.stablediffusion.util

import androidx.compose.ui.graphics.ImageBitmap
import com.vproject.stablediffusion.model.PictureData

expect fun imageBitmapFromBytes(encodedImageData: ByteArray): ImageBitmap

expect class PlatformStorableImage

expect fun createUUID(): String

expect fun getCurrentTimeMillis(): Long

internal expect fun ByteArray.toImageBitmap(): ImageBitmap

interface ImageStorage {
    fun saveImage(picture: PictureData.Camera, image: PlatformStorableImage)
    fun delete(picture: PictureData.Camera)
    fun rewrite(picture: PictureData.Camera)
    suspend fun getThumbnail(picture: PictureData.Camera): ImageBitmap
    suspend fun getImage(picture: PictureData.Camera): ImageBitmap
}

