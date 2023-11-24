package com.vproject.stablediffusion.model

data class Style(
    val id: String,
    val name: String,
    val imageUrl: String,
    val prompt: String,
    val negativePrompt: String
)
