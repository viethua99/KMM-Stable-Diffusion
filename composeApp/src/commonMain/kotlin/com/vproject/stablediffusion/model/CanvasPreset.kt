package com.vproject.stablediffusion.model

enum class CanvasPreset(
    val id: String,
    val aspectRatio: Float,
    val width: Long,
    val height: Long
) {
    THREE_FOUR("3:4", 3 / 4f, 896, 1152),
    ONE_ONE("1:1", 1 / 1f, 1024, 1024),
    NINE_SIXTEEN("9:16", 9 / 16f, 768, 1344),
    FOUR_THREE("4:3", 4 / 3f, 1152, 896),
    SIXTEEN_NINE("16:9", 16 / 9f, 1344, 768),
}
