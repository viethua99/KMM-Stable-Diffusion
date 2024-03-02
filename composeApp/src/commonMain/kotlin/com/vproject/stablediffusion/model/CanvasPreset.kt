package com.vproject.stablediffusion.model

import com.vproject.stablediffusion.SharedRes
import dev.icerock.moko.resources.ImageResource

enum class CanvasPreset(val id: String, val imageResource: ImageResource, val aspectRatio: Float) {
    THREE_FOUR("3:4", SharedRes.images.img_style_preset_anime, 3 / 4f),
    ONE_ONE("1:1", SharedRes.images.img_style_preset_anime, 1 / 1f),
    NINE_SIXTEEN("9:16", SharedRes.images.img_style_preset_anime, 9 / 16f),
    FOUR_THREE("4:3", SharedRes.images.img_style_preset_anime, 4 / 3f),
    SIXTEEN_NINE("16:9", SharedRes.images.img_style_preset_anime, 16 / 9f),
}
