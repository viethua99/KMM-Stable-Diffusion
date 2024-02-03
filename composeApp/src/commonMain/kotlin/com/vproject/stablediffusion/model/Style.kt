package com.vproject.stablediffusion.model

import dev.icerock.moko.resources.ImageResource

data class Style(
    val id: String,
    val name: String,
    val imageResource: ImageResource,
    val prompt: String,
    val negativePrompt: String
)
