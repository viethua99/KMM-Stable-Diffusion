package com.vproject.stablediffusion.model

import com.vproject.stablediffusion.SharedRes
import dev.icerock.moko.resources.ImageResource

enum class CanvasPreset(val id: String, val imageResource: ImageResource) {
    THREE_FOUR("3:4", SharedRes.images.img_style_preset_anime),
    ONE_ONE("1:1", SharedRes.images.img_style_preset_anime),
    NINE_SIXTEEN("9:16", SharedRes.images.img_style_preset_anime),
    FOUR_THREE("4:3", SharedRes.images.img_style_preset_anime),
    SIXTEEN_NINE("16:9", SharedRes.images.img_style_preset_anime),
}
